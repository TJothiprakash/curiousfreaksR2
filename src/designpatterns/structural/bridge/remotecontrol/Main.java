package designpatterns.structural.bridge.remotecontrol;


public class Main {
    public static void main(String[] args) {
        Device radio = new Radio();
        Device tv = new TV();

        RemoteControl radioRemote = new RemoteControl(radio);
        RemoteControl tvRemote = new RemoteControl(tv);

        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.mute();

        tvRemote.togglePower();
        tvRemote.volumeDown();
        tvRemote.mute();
    }
}
