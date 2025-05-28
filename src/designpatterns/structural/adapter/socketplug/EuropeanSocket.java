package designpatterns.structural.adapter.socketplug;

public interface EuropeanSocket {
    void power();
}

class AmericanPlug {
    public void flatPinPower() {
        System.out.println("Powering device with flat pins (American plug)");
    }
}


class SocketAdapter implements EuropeanSocket {
    private AmericanPlug americanPlug;

    public SocketAdapter(AmericanPlug americanPlug) {
        this.americanPlug = americanPlug;
    }

    @Override
    public void power() {
        System.out.println("Adapter converts round to flat pins...");
        americanPlug.flatPinPower();
    }
}



