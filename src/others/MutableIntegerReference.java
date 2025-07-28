package others;

public class MutableIntegerReference {
    static void change(int x) {
        System.out.println(x);
        x = 10;
        System.out.println(x);
    }

    public static void main(String[] args) {

        int a = 5;
        change(a);
        System.out.println(a); // Still prints 5

        //solution 1 use array /*  int[] index = {0}; // This is mutable
        //        index[0]++;        // Now index[0] is 1*/
        // solutino 2 use custom class
        /*class MutableInt {
    int value;
    MutableInt(int v) { value = v; }
}

void change(MutableInt mi) {
    mi.value = 10;
}
*/


        /*A "mutable integer reference" in Java just means "some object or structure
        that can hold an integer and be modified across method calls."
You used int[] index = {0} to serve that exact purpose — well done!*/
    }

}
