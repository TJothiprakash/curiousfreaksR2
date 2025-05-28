package designpatterns.structural.decorator;

public abstract class MessageDecorator implements Message {
    protected Message message;

    public MessageDecorator(Message message) {
        this.message = message;
    }

    public abstract String getContent();
}
