package lowleveldesign.messagebrokersystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Partition {
    private final int id;
    private final List<Message> messages = new ArrayList<>();
    private final AtomicLong offsetCounter = new AtomicLong(0);

    public Partition(int id) {
        this.id = id;
    }

    public synchronized void addMessage(String messageId, String content) {
        long offset = offsetCounter.getAndIncrement();
        messages.add(new Message(messageId, content, offset));
    }

    public synchronized List<Message> getMessagesFromOffset(long offset) {
        List<Message> result = new ArrayList<>();
        for (Message m : messages) {
            if (m.getOffset() >= offset) {
                result.add(m);
            }
        }
        return result;
    }

    public int getId() {
        return id;
    }
}
