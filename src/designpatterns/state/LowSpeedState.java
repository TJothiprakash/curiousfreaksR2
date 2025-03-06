
package designpatterns.state;

public class LowSpeedState implements FanState {
    @Override
    public void handleSpeedChange(Fan fan) {
        System.out.println("Fan is now in High Speed.");
        fan.setState(new HighSpeedState());  // Change to High Speed
    }
}
