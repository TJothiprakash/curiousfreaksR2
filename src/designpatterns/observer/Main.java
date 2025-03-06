package designpatterns.observer;


public class Main {
    public static void main(String[] args) {
        NewspaperPublisher publisher = new NewspaperPublisher();

        // ✅ Creating objects of Subscriber (implements ObserverInterface)
        ObserverInterface sub1 = new Subscriber("Alice");
        ObserverInterface sub2 = new Subscriber("Bob");

        // ✅ Registering subscribers (pass objects that implement ObserverInterface)
        publisher.registerObserver(sub1);
        publisher.registerObserver(sub2);

        // Publish new edition (subscribers will get notified)
        publisher.notifyObservers();
    }
}
