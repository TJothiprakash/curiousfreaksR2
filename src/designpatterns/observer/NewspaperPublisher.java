package designpatterns.observer;


import java.util.ArrayList;
import java.util.List;

public class NewspaperPublisher implements SubjectInterface {
    private List<ObserverInterface> subscribers; // List of subscribers (observers)

    public NewspaperPublisher() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        subscribers.add(observer); // ✅ Here we store the observer (Subscriber object)
    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface subscriber : subscribers) {
            subscriber.update(("New Edition Published!")); // Notify all subscribers
        }
    }
}
