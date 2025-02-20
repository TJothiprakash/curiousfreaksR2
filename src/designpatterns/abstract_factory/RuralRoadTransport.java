package designpatterns.abstract_factory;

import designpatterns.factory.Transportation;

public class RuralRoadTransport implements Transportation
{
    @Override
    public void modeOfTransport()
    {
        System.out.println("Rural Road Transport");
    }
}
