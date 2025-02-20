package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;

public class UrbanSeaTransport implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Urban Sea Transport");
    }
}
