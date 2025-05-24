package designpatterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*Adapter: Use when you want to convert one interface to another expected by clients.
 Useful for integrating legacy code.

Bridge: Separate abstraction from implementation to vary both independently.
Use when you want to avoid permanent binding between abstraction and implementation.

Composite: Use to build tree-like structures of objects where clients treat individual objects
and compositions uniformly. Good for hierarchical data.

Decorator: Attach additional responsibilities dynamically to objects without altering the original class.
 Good for adding features at runtime.

Facade: Provide a simple unified interface to a complex subsystem.
 Use to reduce complexity and dependencies.

Flyweight: Share common parts of state between many objects to save memory.
Use when lots of similar objects are needed.

Proxy: Provide a placeholder to control access, adding lazy initialization,
 access control, or logging.

*/

// StructuralPatternsDemo.java
public class StructuralPatternsDemo {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Demo ===");
        // Usage: Convert interface of one class to another expected by client.
        Socket socket = new Socket();
        SocketAdapter adapter = new SocketObjectAdapter(socket);
        Volt v120 = adapter.get120Volt();
        Volt v12 = adapter.get12Volt();
        System.out.println("Socket Adapter Voltages: 120V = " + v120.getVolts() + ", 12V = " + v12.getVolts());

        System.out.println("\n=== Bridge Pattern Demo ===");
        // Usage: Decouple abstraction from implementation so both can vary independently.
        Shape1 redCircle = new Circle1(5, new RedCircle());
        Shape1 greenCircle = new Circle1(10, new GreenCircle());
        redCircle.draw();
        greenCircle.draw();

        System.out.println("\n=== Composite Pattern Demo ===");
        // Usage: Compose objects into tree structures to represent part-whole hierarchies.
        Employee CEO = new Employee("John", "CEO", 30000);
        Employee headSales = new Employee("Robert", "Head Sales", 20000);
        Employee salesExecutive1 = new Employee("Laura", "Sales", 10000);
        Employee salesExecutive2 = new Employee("Bob", "Sales", 10000);
        Employee headMarketing = new Employee("Michael", "Head Marketing", 20000);
        Employee clerk1 = new Employee("Laura", "Marketing", 10000);
        Employee clerk2 = new Employee("Bob", "Marketing", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        System.out.println("Company Structure:");
        CEO.print();

        System.out.println("\n=== Decorator Pattern Demo ===");
        // Usage: Add responsibilities to objects dynamically.
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Cost: $" + simpleCoffee.getCost() + "; Ingredients: " + simpleCoffee.getIngredients());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Cost: $" + milkCoffee.getCost() + "; Ingredients: " + milkCoffee.getIngredients());

        Coffee whipMilkCoffee = new WhipDecorator(milkCoffee);
        System.out.println("Cost: $" + whipMilkCoffee.getCost() + "; Ingredients: " + whipMilkCoffee.getIngredients());

        System.out.println("\n=== Facade Pattern Demo ===");
        // Usage: Provide a unified interface to a set of interfaces in a subsystem.
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(new Amplifier(), new Tuner(), new DvdPlayer());
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();

        System.out.println("\n=== Flyweight Pattern Demo ===");
        // Usage: Use sharing to support large numbers of fine-grained objects efficiently.
        CoffeeFlavorFactory flavorFactory = new CoffeeFlavorFactory();

        CoffeeOrderContext context1 = new CoffeeOrderContext("Table 1");
        CoffeeOrderContext context2 = new CoffeeOrderContext("Table 2");

        CoffeeFlavor espresso = flavorFactory.getCoffeeFlavor("Espresso");
        CoffeeFlavor latte = flavorFactory.getCoffeeFlavor("Latte");

        espresso.serve(context1);
        latte.serve(context2);

        CoffeeFlavor espresso2 = flavorFactory.getCoffeeFlavor("Espresso");
        espresso2.serve(new CoffeeOrderContext("Table 3"));

        System.out.println("Total CoffeeFlavor objects created: " + flavorFactory.getTotalCoffeeFlavors());

        System.out.println("\n=== Proxy Pattern Demo ===");
        // Usage: Provide a placeholder to control access to another object.
        Image image = new ProxyImage("test_image.jpg");
        image.display(); // Image will be loaded from disk
        image.display(); // Image will not be loaded from disk (cached)
    }
}

// --- Adapter Pattern ---
class Volt {
    private int volts;

    public Volt(int v) {
        volts = v;
    }

    public int getVolts() {
        return volts;
    }
}

interface SocketAdapter {
    Volt get120Volt();

    Volt get12Volt();

    Volt get3Volt();
}

class Socket {
    public Volt getVolt() {
        return new Volt(120);
    }
}

class SocketObjectAdapter implements SocketAdapter {
    private Socket socket = new Socket();

    public SocketObjectAdapter(Socket socket) {

    }

    public Volt get120Volt() {
        return socket.getVolt();
    }

    public Volt get12Volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 10);
    }

    public Volt get3Volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 40);
    }

    private Volt convertVolt(Volt v, int divisor) {
        return new Volt(v.getVolts() / divisor);
    }
}

// --- Bridge Pattern ---
interface DrawAPI {
    void drawCircle(int radius, int x, int y);
}

class RedCircle implements DrawAPI {
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle [Color: Red, radius: " + radius + ", x: " + x + ", y: " + y + "]");
    }
}

class GreenCircle implements DrawAPI {
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle [Color: Green, radius: " + radius + ", x: " + x + ", y: " + y + "]");
    }
}

abstract class Shape1 {
    protected DrawAPI drawAPI;

    protected Shape1(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    abstract void draw();
}

class Circle1 extends Shape1 {
    private int x, y, radius;

    public Circle1(int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.radius = radius;
        this.x = 0;
        this.y = 0;
    }

    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}

// --- Composite Pattern ---


class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name, String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void print() {
        System.out.println("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary + " ]");
        for (Employee e : subordinates) {
            e.print();
        }
    }
}

// --- Decorator Pattern ---
interface Coffee {
    double getCost();

    String getIngredients();
}

class SimpleCoffee implements Coffee {
    public double getCost() {
        return 1.0;
    }

    public String getIngredients() {
        return "Coffee";
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }

    public String getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public double getCost() {
        return super.getCost() + 0.5;
    }

    public String getIngredients() {
        return super.getIngredients() + ", Milk";
    }
}

class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    public double getCost() {
        return super.getCost() + 0.7;
    }

    public String getIngredients() {
        return super.getIngredients() + ", Whip";
    }
}

// --- Facade Pattern ---
class Amplifier {
    public void on() {
        System.out.println("Amplifier on");
    }

    public void off() {
        System.out.println("Amplifier off");
    }

    public void setVolume(int level) {
        System.out.println("Amplifier volume set to " + level);
    }
}

class Tuner {
    public void on() {
        System.out.println("Tuner on");
    }

    public void off() {
        System.out.println("Tuner off");
    }
}

class DvdPlayer {
    public void on() {
        System.out.println("DVD Player on");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void stop() {
        System.out.println("Stopping movie");
    }

    public void off() {
        System.out.println("DVD Player off");
    }
}

class HomeTheaterFacade {
    private Amplifier amp;
    private Tuner tuner;
    private DvdPlayer dvd;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, DvdPlayer dvd) {
        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        amp.on();
        amp.setVolume(10);
        tuner.on();
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        amp.off();
        tuner.off();
        dvd.stop();
        dvd.off();
    }
}

// --- Flyweight Pattern ---


class CoffeeFlavor {
    private String flavor;

    public CoffeeFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void serve(CoffeeOrderContext context) {
        System.out.println("Serving " + flavor + " to table " + context.getTableNumber());
    }
}

class CoffeeOrderContext {
    private String tableNumber;

    public CoffeeOrderContext(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTableNumber() {
        return tableNumber;
    }
}

class CoffeeFlavorFactory {
    private Map<String, CoffeeFlavor> flavors = new HashMap<>();

    public CoffeeFlavor getCoffeeFlavor(String flavorName) {
        CoffeeFlavor flavor = flavors.get(flavorName);
        if (flavor == null) {
            flavor = new CoffeeFlavor(flavorName);
            flavors.put(flavorName, flavor);
            System.out.println("Creating flavor " + flavorName);
        }
        return flavor;
    }

    public int getTotalCoffeeFlavors() {
        return flavors.size();
    }
}

// --- Proxy Pattern ---
interface Image {
    void display();
}

class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }

    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
