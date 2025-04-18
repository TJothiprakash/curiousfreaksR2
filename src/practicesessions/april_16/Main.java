package practicesessions.april_16;

public class Main {
    public static void main(String[] args) {
        CustomStack<Integer> stack = new CustomStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.print(); // 10 -> 20 -> 30 -> null

        System.out.println("Popped: " + stack.pop()); // 30
        System.out.println("Peek: " + stack.peek());   // 20

        stack.print(); // 10 -> 20 -> null

        CustomStack stack2 = new CustomStack<>();
        stack2.push("Hello");
        stack2.push("World");
        stack2.push(10);
        stack2.push(20);
        stack2.push(30);
        stack2.push(false);
        stack2.print();
    }
}

