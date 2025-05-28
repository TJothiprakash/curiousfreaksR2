package designpatterns.structural.adapter.socketplug;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        AmericanPlug oldPlug = new AmericanPlug();
        EuropeanSocket socket = new SocketAdapter(oldPlug); // Adapted
        socket.power(); // Works through adapter
    }
}