package designpatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface ParticleType {
    void display(int x, int y, int velocity, int life);
}

// Concrete Flyweight
class ConcreteParticleType implements ParticleType {
    private String type;
    private String texture;

    public ConcreteParticleType(String type, String texture) {
        this.type = type;
        this.texture = texture;
    }

    @Override
    public void display(int x, int y, int velocity, int life) {
        System.out.println("Displaying " + type + " particle with texture " + texture +
                " at (" + x + "," + y + ") velocity " + velocity + ", life " + life);
    }
}

// Flyweight Factory
class ParticleFactory {
    private static final Map<String, ParticleType> particleTypes = new HashMap<>();

    public static ParticleType getParticleType(String type, String texture) {
        String key = type + "-" + texture;
        if (!particleTypes.containsKey(key)) {
            particleTypes.put(key, new ConcreteParticleType(type, texture));
        }
        return particleTypes.get(key);
    }
}

// Particle context class (holds extrinsic state)
class Particle {
    private int x, y, velocity, life;
    private ParticleType particleType;

    public Particle(int x, int y, int velocity, int life, ParticleType particleType) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.life = life;
        this.particleType = particleType;
    }

    public void display() {
        particleType.display(x, y, velocity, life);
    }
}

// Client
public class Test {
    public static void main(String[] args) {
        ParticleType sparkType = ParticleFactory.getParticleType("Spark", "SparkTexture");
        ParticleType smokeType = ParticleFactory.getParticleType("Smoke", "SmokeTexture");
        System.out.println( sparkType +" "+smokeType);
        Particle p1 = new Particle(10, 20, 5, 100, sparkType);
        Particle p2 = new Particle(15, 25, 3, 80, sparkType);
        Particle p3 = new Particle(50, 60, 1, 200, smokeType);
        System.out.println(p1 +" "+ p2 +" "+ p3);

        p1.display();
        p2.display();
        p3.display();
    }
}

