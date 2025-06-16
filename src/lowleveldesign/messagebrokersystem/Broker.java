package lowleveldesign.messagebrokersystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    private final Map<String, Topic> topics = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Map<Integer, Long>>> consumerOffsets = new ConcurrentHashMap<>();
    // topic → groupId → partitionId → offset

    public void createTopic(String topicName, int partitions) {
        topics.put(topicName, new Topic(topicName, partitions));
    }

    public void publish(String topicName, String messageId, String content) {
        Topic topic = topics.get(topicName);
        if (topic == null) throw new RuntimeException("Topic does not exist: " + topicName);

        Partition partition = topic.getNextPartition();
        partition.addMessage(messageId, content);
        System.out.println("Published to " + topicName + " [partition " + partition.getId() + "]");
    }

    public void subscribe(String topicName, String groupId, String consumerId) {
        Topic topic = topics.get(topicName);
        if (topic == null) throw new RuntimeException("Topic does not exist: " + topicName);

        consumerOffsets.putIfAbsent(topicName, new ConcurrentHashMap<>());
        Map<String, Map<Integer, Long>> groupMap = consumerOffsets.get(topicName);
        groupMap.putIfAbsent(groupId, new ConcurrentHashMap<>());

        Map<Integer, Long> partitionOffsets = groupMap.get(groupId);
        for (Partition p : topic.getPartitions()) {
            partitionOffsets.putIfAbsent(p.getId(), 0L);
        }

        System.out.println("Consumer " + consumerId + " subscribed to " + topicName + " in group " + groupId);
    }

    public List<Message> poll(String topicName, String groupId, String consumerId) {
        Topic topic = topics.get(topicName);
        if (topic == null) throw new RuntimeException("Topic does not exist: " + topicName);

        Map<Integer, Long> offsets = consumerOffsets
                .getOrDefault(topicName, Collections.emptyMap())
                .getOrDefault(groupId, Collections.emptyMap());

        List<Message> result = new ArrayList<>();
        for (Partition p : topic.getPartitions()) {
            long offset = offsets.getOrDefault(p.getId(), 0L);
            List<Message> messages = p.getMessagesFromOffset(offset);
            if (!messages.isEmpty()) {
                result.addAll(messages);
                consumerOffsets.get(topicName).get(groupId).put(p.getId(), messages.get(messages.size() - 1).getOffset() + 1);
            }
        }

        return result;
    }
}

