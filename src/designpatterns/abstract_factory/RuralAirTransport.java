package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;

public class RuralAirTransport implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Rural Air Transport");
    }
}
