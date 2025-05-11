package LLD.raidHaliingDispatch;


//

/* 1. Design a ride-hailing dispatch system (like Uber/Lyft)
Requirements:

Match passengers to nearest drivers in real time.

Handle surge pricing zones.

Support driver states (available, busy, offline).

Track location updates from thousands of drivers.

Optimize for minimal wait time and driver utilization.

Allow scheduled rides.

Handle edge cases like cancellations and no-shows.*/

/*
 driver, passenger, ride
  class driver ->  driver id, name, vehicleId, currentLocation, Enum DrivercurrentState,

  List<Rides> = find driver utilisation
  handlePrices = > also do surcharges based on locations

  class passenger -> name, passengerId, boardingLocation, destinationLocation, WaitingTime, ETA,
  class Ride -> @passenger, @driver associate the classes
 List<Driver>
   findNearestDriver(){
   using the driver &   passenger locations find the distance
  }
 findMinimalTime() {

  }




*/

public class Main {
    public static void main(String[] args){
        System.out.println("Hello ");
    }
}
