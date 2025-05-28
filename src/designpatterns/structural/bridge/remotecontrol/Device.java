package designpatterns.structural.bridge.remotecontrol;

public interface Device {
    void on();
    void off();
    void setVolume(int volume);
    int getVolume();
    boolean isEnabled();
}
abstract class AbstractRemoteControl {
    protected Device device;

    public AbstractRemoteControl(Device device) {
        this.device = device;
    }

    public abstract void togglePower();
    public abstract void volumeUp();
    public abstract void volumeDown();
}
class RemoteControl extends AbstractRemoteControl {

    public RemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
        System.out.println("Device muted.");
    }

    @Override
    public void togglePower() {
        if (device.isEnabled()) {
            device.off();
        } else {
            device.on();
        }
    }

    @Override
    public void volumeUp() {
        int currentVolume = device.getVolume();
        device.setVolume(currentVolume + 1);
        System.out.println("Volume increased to: " + device.getVolume());
    }

    @Override
    public void volumeDown() {
        int currentVolume = device.getVolume();
        device.setVolume(Math.max(0, currentVolume - 1));
        System.out.println("Volume decreased to: " + device.getVolume());
    }
}
class Radio implements Device {
    private boolean isOn = false;
    private int volume = 5;

    @Override
    public void on() {
        isOn = true;
        System.out.println("Radio is ON");
    }

    @Override
    public void off() {
        isOn = false;
        System.out.println("Radio is OFF");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean isEnabled() {
        return isOn;
    }
}
class TV implements Device {
    private boolean isOn = false;
    private int volume = 10;

    @Override
    public void on() {
        isOn = true;
        System.out.println("TV is ON");
    }

    @Override
    public void off() {
        isOn = false;
        System.out.println("TV is OFF");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean isEnabled() {
        return isOn;
    }
}
