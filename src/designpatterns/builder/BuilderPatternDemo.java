package designpatterns.builder;


public class BuilderPatternDemo {
    public static void main(String[] args) {
        // **Building a car with only required parameters**
        Car basicCar = new CarBuilder("V4 Engine", 4).build();
        basicCar.showCarDetails();

        System.out.println("\n-----------------\n");

        // **Building a luxury car with optional parameters**
        Car luxuryCar = new CarBuilder("V8 Engine", 4)
                .setSunroof(true)
                .setGPS(true)
                .setAirConditioning(true)
                .build();
        luxuryCar.showCarDetails();
    }
}
