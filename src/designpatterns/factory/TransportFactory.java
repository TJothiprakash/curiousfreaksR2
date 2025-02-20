package designpatterns.factory;

public class TransportFactory {

    public static Transportation getTransport(String modeOfTransport) {
        if (modeOfTransport == null) {
            return null;
        }
        if (modeOfTransport.equalsIgnoreCase("Sea")) {
            return new SeaTransportation();
        } else if (modeOfTransport.equalsIgnoreCase("Air")) {
            return new AirTransportation();
        }
        if (modeOfTransport.equalsIgnoreCase("Road")) {
            return new RoadTransport();
        }
        return null;
    }
}
