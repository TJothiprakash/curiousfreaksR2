package queue.may_24_2025;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StackImplUsingQueueTest {

    private StackImplUsingQueue stack;

    @BeforeEach
    void setUp() {
        stack = new StackImplUsingQueue();
    }

    @Test
    void testPushAndPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testPeek() {
        stack.push(5);
        stack.push(10);
        assertEquals(10, stack.peek());
        assertEquals(10, stack.peek()); // ensure peek does not remove it
    }

    @Test
    void testIsEmptyAndSize() {
        assertTrue(stack.isEmpty());
        stack.push(100);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopOnEmptyStack() {
        Exception exception = assertThrows(RuntimeException.class, stack::pop);
        assertEquals("Stack is empty", exception.getMessage());
    }

    @Test
    void testPeekOnEmptyStack() {
        Exception exception = assertThrows(RuntimeException.class, stack::peek);
        assertEquals("Stack is empty", exception.getMessage());
    }
}

