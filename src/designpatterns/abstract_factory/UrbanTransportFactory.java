package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;

public class UrbanTransportFactory implements TransportFactory {
    @Override
    public Transportation createRoadTransport() {
        return new UrbanRoadTransport();
    }

    @Override
    public Transportation createSeaTransport() {
        return new UrbanSeaTransport();
    }

    @Override
    public Transportation createAirTransport() {
        return new UrbanAirTransport();
    }
}
