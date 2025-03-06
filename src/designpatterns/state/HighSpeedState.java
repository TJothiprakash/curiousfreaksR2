package designpatterns.state;

public class HighSpeedState  implements FanState{
    @Override
    public void handleSpeedChange(Fan fan) {
        System.out.println("Fan is now in Low Speed.");
        fan.setState(new LowSpeedState());  // Change to Low Speed

    }
}
