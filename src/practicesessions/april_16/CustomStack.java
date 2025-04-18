package practicesessions.april_16;

public class CustomStack<T> {
    private int DEFAULT_SIZE = 10;
    private Object[] stack;
    private int top;

    public CustomStack() {
        stack = new Object[DEFAULT_SIZE];
        top = -1;
    }

    public void push(T data) {
        if (top == stack.length - 1) {
            Object[] temp = resizeArray();
            stack = temp;

        }
        stack[++top] = data;
    }

    private Object[] resizeArray() {
        Object[] temp = new Object[stack.length * 2];
        System.arraycopy(stack, 0, temp, 0, stack.length);
        return temp;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T item = (T) stack[top];
        stack[top--] = null;
        return (T) item;
    }

    public boolean isEmpty() {

        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (T) stack[top];
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        stack = new Object[DEFAULT_SIZE];
        top = -1;
    }

    public void print() {
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " -> ");
        }
        System.out.println("null");
    }


}
