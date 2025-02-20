package designpatterns.factory;

public class AirTransportation implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Air Transport");
    }
}
