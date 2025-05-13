package lld.messagebrokersystem;
public class SimpleProducer implements Producer {
    private final Broker broker;

    public SimpleProducer(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void send(String topic, String messageId, String content) {
        broker.publish(topic, messageId, content);
    }
}

