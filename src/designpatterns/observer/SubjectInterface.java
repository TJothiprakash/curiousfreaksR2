package designpatterns.observer;

public interface SubjectInterface {
    void registerObserver(ObserverInterface observer);

    void removeObserver(ObserverInterface observer);

    void notifyObservers();
}
