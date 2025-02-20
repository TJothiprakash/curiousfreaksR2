package designpatterns.abstract_factory;


import designpatterns.factory.Transportation;

public class UrbanRoadTransport implements Transportation {
    @Override
    public void modeOfTransport() {
        System.out.println("Urban Road Transport");
    }
}
