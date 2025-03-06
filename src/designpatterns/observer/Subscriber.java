package designpatterns.observer;


public class Subscriber implements ObserverInterface {
    private String subscriberName;

    public Subscriber(String name) {
        this.subscriberName = name;
    }

    @Override
    public void update(String newspaperEdition) {
        System.out.println(subscriberName + " received newspaper: " + newspaperEdition);
    }




}
