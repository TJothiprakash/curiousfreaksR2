package lowleveldesign.messagebrokersystem;

import java.util.List;

public class SimpleConsumer implements Consumer {
    private final Broker broker;
    private final String consumerId;

    public SimpleConsumer(Broker broker, String consumerId) {
        this.broker = broker;
        this.consumerId = consumerId;
    }

    @Override
    public void subscribe(String topic, String groupId) {
        broker.subscribe(topic, groupId, consumerId);
    }

    @Override
    public List<Message> poll(String topic, String groupId) {
        return broker.poll(topic, groupId, consumerId);
    }
}

