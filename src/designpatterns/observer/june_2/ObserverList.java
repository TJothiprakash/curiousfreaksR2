package designpatterns.observer.june_2;

import java.util.ArrayList;
import java.util.List;

public class ObserverList {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void updateObservers(Observer observer) {
        for (int i = 0; i < observers.size(); i++) {
            Observer o = observers.get(i);
            if (o.id.equals(observer.id)) {
                observers.set(i, observer);
            }
        }
    }

    public List<Observer> getList() {
        return observers;
    }
}
