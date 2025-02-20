package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        // Get the Urban Transport Factory
        TransportFactory urbanFactory = FactoryProvider.getFactory("urban");

        // Create Urban Road Transport
        Transportation urbanRoad = urbanFactory.createRoadTransport();
        urbanRoad.modeOfTransport(); // Output: Urban Road Transport

        // Create Urban Air Transport
        Transportation urbanAir = urbanFactory.createAirTransport();
        urbanAir.modeOfTransport(); // Output: Urban Air Transport

        // Get the Rural Transport Factory
        TransportFactory ruralFactory = FactoryProvider.getFactory("rural");

        // Create Rural Sea Transport
        Transportation ruralSea = ruralFactory.createSeaTransport();
        ruralSea.modeOfTransport(); // Output: Rural Sea Transport
    }
}

