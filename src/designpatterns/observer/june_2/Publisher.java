package designpatterns.observer.june_2;

import java.util.List;

public class Publisher {

    private ObserverList observers;

    public Publisher(ObserverList observerList) {
        this.observers = observerList;
    }

    public void notifyAllObservers() {
        List<Observer> observerList = observers.getList();
        for (Observer o : observerList) {
            o.notifyObserver();
        }
    }
}
