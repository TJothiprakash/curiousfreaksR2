package designpatterns;

public class CreationalPatternsPractice {
}

/*Pattern	Usage	When to Apply
Singleton	Ensure only one instance of a class exists, provide a global point of access.
For shared resources like config, logger, connection pools, caches.
Factory Method
	Encapsulates object creation, returns objects via common interface without exposing class.
		When a class can't anticipate the type of objects it must create, or for flexibility in instantiation.
Abstract Factory
Creates families of related objects without specifying concrete classes.
	When you want to work with multiple related products from a family, e.g., UI controls for OS.
Builder	Constructs
 complex objects step-by-step with readable code, useful for many optional params.
 	When creating objects with many optional parameters or complex construction logic.
Prototype
	Creates new objects by cloning existing ones, avoiding costly creation operations.
		When object creation is expensive and you want to avoid building from scratch repeatedly.
*/
// CreationalPatternsDemo.java
 class CreationalPatternsDemo {
    public static void main(String[] args) {
        System.out.println("--- Singleton Variations Demo ---");

        // 1. Singleton Variations
        SingletonEager eager1 = SingletonEager.getInstance();
        SingletonEager eager2 = SingletonEager.getInstance();
        System.out.println("SingletonEager instances same? " + (eager1 == eager2));
        System.out.println();

        SingletonLazy lazy1 = SingletonLazy.getInstance();
        SingletonLazy lazy2 = SingletonLazy.getInstance();
        System.out.println("SingletonLazy instances same? " + (lazy1 == lazy2));
        System.out.println();

        SingletonThreadSafe ts1 = SingletonThreadSafe.getInstance();
        SingletonThreadSafe ts2 = SingletonThreadSafe.getInstance();
        System.out.println("SingletonThreadSafe instances same? " + (ts1 == ts2));
        System.out.println();

        SingletonDoubleChecked dc1 = SingletonDoubleChecked.getInstance();
        SingletonDoubleChecked dc2 = SingletonDoubleChecked.getInstance();
        System.out.println("SingletonDoubleChecked instances same? " + (dc1 == dc2));
        System.out.println("\n");

        // 2. Factory Method Pattern
        System.out.println("--- Factory Method Pattern Demo ---");
        Shape circle = ShapeFactory.getShape("CIRCLE");
        Shape square = ShapeFactory.getShape("SQUARE");
        circle.draw();
        square.draw();
        System.out.println();

        // 3. Abstract Factory Pattern
        System.out.println("--- Abstract Factory Pattern Demo ---");
        GUIFactory winFactory = FactoryProvider.getFactory("Windows");
        Button winButton = winFactory.createButton();
        winButton.paint();

        GUIFactory macFactory = FactoryProvider.getFactory("Mac");
        Button macButton = macFactory.createButton();
        macButton.paint();
        System.out.println();

        // 4. Builder Pattern
        System.out.println("--- Builder Pattern Demo ---");
        Computer computer = new Computer.Builder("i7", "16GB")
                .setStorage("1TB SSD")
                .setGraphics("NVIDIA RTX 3080")
                .build();
        computer.showConfig();
        System.out.println();

        // 5. Prototype Pattern
        System.out.println("--- Prototype Pattern Demo ---");
        ShapePrototype original = new RectanglePrototype(10, 20);
        ShapePrototype cloned = original.clone();
        original.draw();
        cloned.draw();
    }
}

// --- 1. Singleton Pattern Variations ---

// 1. Eager Initialization (Thread-safe by default)
class SingletonEager {
    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {
        System.out.println("SingletonEager instance created.");
    }

    public static SingletonEager getInstance() {
        System.out.println("SingletonEager: Returning instance.");
        return instance;
    }
}

// 2. Lazy Initialization (Not thread-safe)
class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
        System.out.println("SingletonLazy instance created.");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            System.out.println("SingletonLazy: Creating new instance...");
            instance = new SingletonLazy();
        } else {
            System.out.println("SingletonLazy: Returning existing instance.");
        }
        return instance;
    }
}

// 3. Thread-Safe Singleton using synchronized method (Lazy but slow)
class SingletonThreadSafe {
    private static SingletonThreadSafe instance;

    private SingletonThreadSafe() {
        System.out.println("SingletonThreadSafe instance created.");
    }

    public static synchronized SingletonThreadSafe getInstance() {
        if (instance == null) {
            System.out.println("SingletonThreadSafe: Creating new instance...");
            instance = new SingletonThreadSafe();
        } else {
            System.out.println("SingletonThreadSafe: Returning existing instance.");
        }
        return instance;
    }
}

// 4. Double-Checked Locking (Thread-safe with better performance)
class SingletonDoubleChecked {
    private static volatile SingletonDoubleChecked instance;

    private SingletonDoubleChecked() {
        System.out.println("SingletonDoubleChecked instance created.");
    }

    public static SingletonDoubleChecked getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleChecked.class) {
                if (instance == null) {
                    System.out.println("SingletonDoubleChecked: Creating new instance...");
                    instance = new SingletonDoubleChecked();
                } else {
                    System.out.println("SingletonDoubleChecked: Instance already created inside synchronized block.");
                }
            }
        } else {
            System.out.println("SingletonDoubleChecked: Returning existing instance.");
        }
        return instance;
    }
}

// --- 2. Factory Method Pattern ---
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Circle Drawn");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Square Drawn");
    }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        if (type == null) return null;
        if (type.equalsIgnoreCase("CIRCLE")) {
            System.out.println("ShapeFactory: Creating Circle.");
            return new Circle();
        }
        if (type.equalsIgnoreCase("SQUARE")) {
            System.out.println("ShapeFactory: Creating Square.");
            return new Square();
        }
        System.out.println("ShapeFactory: Unknown shape type.");
        return null;
    }
}

// --- 3. Abstract Factory Pattern ---
interface Button {
    void paint();
}

class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows Button");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac Button");
    }
}

interface GUIFactory {
    Button createButton();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        System.out.println("WindowsFactory: Creating Windows Button.");
        return new WindowsButton();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        System.out.println("MacFactory: Creating Mac Button.");
        return new MacButton();
    }
}

class FactoryProvider {
    public static GUIFactory getFactory(String osType) {
        if (osType == null) return null;
        if (osType.equalsIgnoreCase("Windows")) {
            System.out.println("FactoryProvider: Providing WindowsFactory.");
            return new WindowsFactory();
        }
        if (osType.equalsIgnoreCase("Mac")) {
            System.out.println("FactoryProvider: Providing MacFactory.");
            return new MacFactory();
        }
        System.out.println("FactoryProvider: Unknown OS type.");
        return null;
    }
}

// --- 4. Builder Pattern ---
class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String graphics;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphics = builder.graphics;
    }

    public void showConfig() {
        System.out.println("CPU: " + cpu + ", RAM: " + ram + ", Storage: " + storage + ", Graphics: " + graphics);
    }

    static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String graphics;

        public Builder(String cpu, String ram) {
            System.out.println("Builder: Creating builder with CPU and RAM.");
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder setStorage(String storage) {
            System.out.println("Builder: Setting storage to " + storage);
            this.storage = storage;
            return this;
        }

        public Builder setGraphics(String graphics) {
            System.out.println("Builder: Setting graphics to " + graphics);
            this.graphics = graphics;
            return this;
        }

        public Computer build() {
            System.out.println("Builder: Building Computer object.");
            return new Computer(this);
        }
    }
}

// --- 5. Prototype Pattern ---
interface ShapePrototype {
    ShapePrototype clone();
    void draw();
}

class RectanglePrototype implements ShapePrototype {
    private int width;
    private int height;

    public RectanglePrototype(int width, int height) {
        System.out.println("RectanglePrototype: Creating new rectangle " + width + "x" + height);
        this.width = width;
        this.height = height;
    }

    public ShapePrototype clone() {
        System.out.println("RectanglePrototype: Cloning rectangle.");
        return new RectanglePrototype(this.width, this.height);
    }

    public void draw() {
        System.out.println("Drawing Rectangle: " + width + "x" + height);
    }
}
