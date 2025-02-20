package designpatterns.factory;

public class SeaTransportation implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Sea Transport");
    }
}
