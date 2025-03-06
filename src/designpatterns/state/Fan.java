package designpatterns.state;

public class Fan {
    private FanState currentState;

    public Fan() {
        this.currentState = new LowSpeedState(); // Start with Low Speed
    }

    public void setState(FanState state) {
        this.currentState = state;
    }

    public void pressSpeedButton() {
        currentState.handleSpeedChange(this);  // Change the state
    }
}
