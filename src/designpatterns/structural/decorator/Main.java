package designpatterns.structural.decorator;

public class Main {
    public static void main(String[] args) {
        Message rawMessage = new TextMessage("Hello World");

        // Chain decorators: Logging -> Compression -> Encryption
        Message decoratedMessage = new EncryptionDecorator(
                new CompressionDecorator(
                        new LoggingDecorator(rawMessage)));

        System.out.println("Final Message: " + decoratedMessage.getContent());
    }
}
