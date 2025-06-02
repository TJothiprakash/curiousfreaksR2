package designpatterns.observer.june_2;

public class Observer implements Notify {
    @Override
    public void notifyObserver() {
        System.out.println("sendign notification to  " + this.name);
    }

    public Observer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;
    public String id;
}
