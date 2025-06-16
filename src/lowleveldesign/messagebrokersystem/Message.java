package lowleveldesign.messagebrokersystem;

public class Message {
    private final String id;
    private final String content;
    private final long timestamp;
    private final long offset;

    public Message(String id, String content, long offset) {
        this.id = id;
        this.content = content;
        this.offset = offset;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "Message{" + "id='" + id + '\'' + ", content='" + content + '\'' + ", offset=" + offset + '}';
    }
}

