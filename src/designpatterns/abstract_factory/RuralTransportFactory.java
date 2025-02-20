package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;
public class RuralTransportFactory implements TransportFactory
{
    @Override
    public Transportation createRoadTransport() {
        return new RuralRoadTransport();
    }

    @Override
    public Transportation createSeaTransport() {
        return new RuralSeaTransport();
    }

    @Override
    public Transportation createAirTransport() {
        return new RuralAirTransport();
    }

}
