package designpatterns.observer.june_2;



/*| Role                     | Responsibility                              |
| ------------------------ | ------------------------------------------- |
| `Subject` or `Publisher` | Holds observer list and sends notifications |
| `Observer`               | Defines how to respond to updates           |
| `Notify` (Interface)     | Declares `notifyObserver()` or `update()`   |
*/
class Main {
    public static void main(String[] args) {

        ObserverList list = new ObserverList();
        list.addObserver(new Observer("1", "Alice"));
        list.addObserver(new Observer("2", "Bob"));
        list.addObserver(new Observer("3", "Carol"));
        list.addObserver(new Observer("4", "David"));
        list.addObserver(new Observer("5", "Eve"));
        list.addObserver(new Observer("6", "Frank"));
        list.addObserver(new Observer("7", "Grace"));
        list.addObserver(new Observer("8", "Heidi"));
        list.addObserver(new Observer("9", "Ivy"));
        list.addObserver(new Observer("10", "Judy"));

        Publisher publisher = new Publisher(list);
        publisher.notifyAllObservers();

    }
}
