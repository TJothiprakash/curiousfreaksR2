package april_16;

import java.util.Arrays;

public class CustomArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size = 0;

    public CustomArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    // Add a new element
    public void add(T t) {
        if (size == array.length) {
            resize(array.length);
        }
        array[size++] = t;
        System.out.println("Adding: " + t);
        System.out.println("Current size: " + size);
        System.out.println("Array: " + Arrays.toString(array));
    }

    // Get element at index
    public T get(int index) {
        validateIndex(index);
        return (T) array[index];
    }

    // Update element at index
    public void update(int index, T t) {
        validateIndex(index);
        array[index] = t;
        System.out.println("Updated index " + index + " with value: " + t);
    }

    // Remove element at index
    public void remove(int index) {
        validateIndex(index);
        System.out.println("Removing: " + array[index]);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null; // clear the last element
        System.out.println("Array after removal: " + Arrays.toString(array));
    }

    // Resize internal array
    private void resize(int currentLength) {
        Object[] newArray = new Object[currentLength * 2];
        System.arraycopy(array, 0, newArray, 0, currentLength);
        array = newArray;
        System.out.println("Resized array to length: " + array.length);
    }

    // Check index validity
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is out of bounds. Size: " + size
            );
        }
    }
}
