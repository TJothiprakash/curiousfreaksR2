package designpatterns.structural.decorator;
public class EncryptionDecorator extends MessageDecorator {
    public EncryptionDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        return "Encrypted(" + message.getContent() + ")";
    }
}

 class CompressionDecorator extends MessageDecorator {
    public CompressionDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        return "Compressed(" + message.getContent() + ")";
    }
}

 class LoggingDecorator extends MessageDecorator {
    public LoggingDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        System.out.println("Logging: " + message.getContent());
        return message.getContent();
    }
}
