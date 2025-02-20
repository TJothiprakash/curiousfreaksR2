package designpatterns.builder;


public class Car {
    // Required Parameters
    private final String engine;
    private final int wheels;

    // Optional Parameters
    private final boolean sunroof;
    private final boolean gps;
    private final boolean airConditioning;

    // Private Constructor (Only the Builder can create Car objects)
    Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.sunroof = builder.sunroof;

        this.gps = builder.gps;
        this.airConditioning = builder.airConditioning;
    }

    // Display car details
    public void showCarDetails() {
        System.out.println("Car Details: ");
        System.out.println("Engine: " + engine);
        System.out.println("Wheels: " + wheels);
        System.out.println("Sunroof: " + (sunroof ? "Yes" : "No"));
        System.out.println("GPS: " + (gps ? "Yes" : "No"));
        System.out.println("Air Conditioning: " + (airConditioning ? "Yes" : "No"));
    }

}