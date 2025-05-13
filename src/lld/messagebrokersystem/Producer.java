package lld.messagebrokersystem;
public interface Producer {
    void send(String topic, String messageId, String content);
}

