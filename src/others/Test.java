package others;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {


        Example obj = null;
        obj.staticMethod(); // No NullPointerException!


        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b); // true
        Integer x = 128;
        Integer y = 128;
        System.out.println(x == y); // false

/*Because Integer.valueOf() reuses instances from -128 to 127,
    comparisons using == work within this range but fail outside it.*/


        enum Singleton {
            INSTANCE;
        }
// Using reflection to access constructor
        Constructor<?> constructor = Singleton.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        //Singleton instance = (Singleton) constructor.newInstance(); // Throws exception!


    }

    class Example {
        static void staticMethod() {
            System.out.println("Static method called");
        }
    }
}

/*Adding strictfp forces Java to follow IEEE 754 strictly,
 preventing platform-dependent optimizations.
* */

class Precision {
    public static void main(String[] args) {
        double num = 0.1 + 0.2;
        System.out.println(num); // 0.30000000000000004
    }
}