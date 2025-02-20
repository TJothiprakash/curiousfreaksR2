package designpatterns.abstract_factory;


public class FactoryProvider {
    public static TransportFactory getFactory(String environment) {
        if ("urban".equalsIgnoreCase(environment)) {
            return new UrbanTransportFactory();
        } else if ("rural".equalsIgnoreCase(environment)) {
            return new RuralTransportFactory();
        }
        throw new IllegalArgumentException("Invalid environment type: " + environment);
    }
}

