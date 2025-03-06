package designpatterns.state;

public class Main {
    public static void main(String[] args) {
        Fan fan = new Fan();

        // Press button multiple times to change speed
        fan.pressSpeedButton(); // Low → High
        fan.pressSpeedButton(); // High → Low
        fan.pressSpeedButton(); // Low → High
    }
}
