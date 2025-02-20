package designpatterns.builder;

// **Builder Class**
public class CarBuilder {
    // Required Parameters
    final String engine;
    final int wheels;

    // Optional Parameters (Default Values)
    boolean sunroof = false;
    boolean gps = false;
    boolean airConditioning = false;

    // Constructor for required parameters
    public CarBuilder(String engine, int wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    // Setter methods for optional features (returning `this` for chaining)
    public CarBuilder setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
        return this;
    }

    public CarBuilder setGPS(boolean gps) {
        this.gps = gps;
        return this;
    }

    public CarBuilder setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
        return this;
    }

    // Build method to return the final Car object
    public Car build() {
        return new Car(this);
    }
}

