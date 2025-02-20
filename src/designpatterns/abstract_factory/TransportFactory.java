package designpatterns.abstract_factory;


import designpatterns.factory.Transportation;

public interface TransportFactory {
    Transportation createRoadTransport();
    Transportation createSeaTransport();
    Transportation createAirTransport();
}

