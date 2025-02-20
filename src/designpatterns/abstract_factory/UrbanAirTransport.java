package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;

public class UrbanAirTransport implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Urban Air Transport");
    }
}
