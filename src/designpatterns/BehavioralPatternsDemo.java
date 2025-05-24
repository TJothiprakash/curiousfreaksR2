package designpatterns;
// BehavioralPatternsDemo.java

import java.util.*;

/*1. Observer Pattern
Purpose:
Defines a one-to-many dependency between objects so when one object changes state, all its dependents are notified and updated automatically.

Use When:

You want to keep multiple objects in sync without tight coupling.

Examples: Event handling systems, UI data binding, distributed event listeners.

2. Strategy Pattern
Purpose:
Defines a family of algorithms, encapsulates each one, and makes them interchangeable. The client can switch algorithms at runtime.

Use When:

You need different variations of an algorithm implemented separately.

You want to avoid conditional statements to select behavior.

Examples: Sorting algorithms, payment methods, compression algorithms.

3. Command Pattern
Purpose:
Encapsulates a request as an object, allowing parameterization of clients with queues, requests, and operations.

Use When:

You want to decouple the object invoking an operation from the one that knows how to perform it.

You want to support undo/redo, logging, or transactional behavior.

Examples: GUI buttons triggering commands, remote controls, task scheduling.

4. Iterator Pattern
Purpose:
Provides a way to access elements of a collection sequentially without exposing its underlying representation.

Use When:

You want to traverse different types of collections uniformly.

You want to hide the internal structure of the collection.

Examples: Traversing lists, trees, or custom data structures.

5. State Pattern
Purpose:
Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

Use When:

The object's behavior depends on its state, and it must change behavior at runtime.

You want to avoid large conditional statements for state-specific behavior.

Examples: TCP connection states, UI element states, workflow stages.

6. Template Method Pattern
Purpose:
Defines the skeleton of an algorithm in a method, deferring some steps to subclasses without changing the algorithm’s structure.

Use When:

You want to define a generic workflow with customizable steps.

You want to avoid code duplication for algorithms sharing a structure.

Examples: Game development stages, parsing workflows, data processing pipelines.

Summary for Applying Behavioral Patterns:
Pattern	When to Use / Benefit
Observer	Automatically notify many objects of state changes.
Strategy	Choose algorithm/behavior dynamically and cleanly.
Command	Encapsulate actions for undo, queuing, or decoupling.
Iterator	Traverse collections uniformly without exposing internals.
State	Change object behavior depending on internal state.
Template	Define fixed algorithm structure but allow flexible steps.

*/


public class BehavioralPatternsDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo ===");
        Subject subject = new Subject();
        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);
        System.out.println("Setting state to 15");
        subject.setState(15);
        System.out.println("Setting state to 10");
        subject.setState(10);

        System.out.println("\n=== Strategy Pattern Demo ===");
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));
        context.setStrategy(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
        context.setStrategy(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        System.out.println("\n=== Command Pattern Demo ===");
        RemoteControl remote = new RemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);
        remote.setCommand(lightOn);
        remote.pressButton();
        remote.setCommand(lightOff);
        remote.pressButton();

        System.out.println("\n=== Iterator Pattern Demo ===");
        NameRepository names = new NameRepository();
        Iterator<String> iterator = names.getIterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println("Name: " + name);
        }

        System.out.println("\n=== State Pattern Demo ===");
        ContextState contextState = new ContextState();
        contextState.request(); // State A
        contextState.request(); // State B
        contextState.request(); // State A

        System.out.println("\n=== Template Method Pattern Demo ===");
        Game football = new Football();
        football.play();

        Game cricket = new Cricket();
        cricket.play();
    }
}

// ========== Observer Pattern ==========
interface Observer {
    void update();
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

class HexObserver implements Observer {
    private Subject subject;

    public HexObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}

class OctalObserver implements Observer {
    private Subject subject;

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}

class BinaryObserver implements Observer {
    private Subject subject;

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

// ========== Strategy Pattern ==========
interface Strategy {
    int doOperation(int num1, int num2);
}

class OperationAdd implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationSubtract implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMultiply implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

// ========== Command Pattern ==========
interface Command {
    void execute();
}

class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }
}

class RemoteControl {
    private Command slot;

    public void setCommand(Command command) {
        slot = command;
    }

    public void pressButton() {
        slot.execute();
    }
}

// ========== Iterator Pattern ==========
interface Iterator<E> {
    boolean hasNext();

    E next();
}

interface Container<E> {
    Iterator<E> getIterator();
}

class NameRepository implements Container<String> {
    public String[] names = {"Robert", "John", "Julie", "Lora"};

    public Iterator<String> getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator<String> {
        int index;

        public boolean hasNext() {
            return index < names.length;
        }

        public String next() {
            if (hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

// ========== State Pattern ==========
interface State {
    void handle(ContextState context);
}

class ContextState {
    private State state;

    public ContextState() {
        state = new StateA();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void request() {
        state.handle(this);
    }
}

class StateA implements State {
    public void handle(ContextState context) {
        System.out.println("State A handling request. Switching to State B.");
        context.setState(new StateB());
    }
}

class StateB implements State {
    public void handle(ContextState context) {
        System.out.println("State B handling request. Switching to State A.");
        context.setState(new StateA());
    }
}

// ========== Template Method Pattern ==========
abstract class Game {
    abstract void initialize();

    abstract void startPlay();

    abstract void endPlay();

    //template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
}

class Football extends Game {
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

class Cricket extends Game {
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

