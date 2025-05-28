package designpatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface TreeType {
    void draw(int x, int y);  // x,y are extrinsic
}

// Concrete Flyweight
class ConcreteTreeType implements TreeType {
    private String name;          // intrinsic
    private String color;         // intrinsic
    private String texture;       // intrinsic

    public ConcreteTreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Drawing " + name + " tree of color " + color + " at (" + x + "," + y + ")");
    }
}

// Flyweight Factory
class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new ConcreteTreeType(name, color, texture));
        }
        return treeTypes.get(key);
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        TreeType oak = TreeFactory.getTreeType("Oak", "Green", "Rough");
        TreeType pine = TreeFactory.getTreeType("Pine", "Dark Green", "Smooth");
        TreeType anotherOak = TreeFactory.getTreeType("Oak", "Green", "Rough");

        System.out.println(oak.getClass().getName()+" "+ oak);
        System.out.println(pine.getClass().getName()+" "+ pine);
        System.out.println(anotherOak.getClass().getName()+" "+ anotherOak);


        // both oak and anotherOak are
        // same instance!
        System.out.println(oak == anotherOak); // true

        oak.draw(10, 20);
        pine.draw(15, 30);
        anotherOak.draw(20, 40);
    }
}

