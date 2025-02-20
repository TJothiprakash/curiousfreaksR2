package designpatterns.factory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        Transportation transport = TransportFactory.getTransport("sea");
        transport.modeOfTransport(); // Output: Sea Transport
    }
}
