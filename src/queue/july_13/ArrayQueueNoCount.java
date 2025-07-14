package queue.july_13;

/************************************************************
 * ✅ Problem (revisited)
 * ----------------------
 * Same queue spec as before, **but** we must *not* keep a
 * separate `count`.  We will detect:
 *   • empty  → front == rear
 *   • full   → (rear + 1) % capacity == front
 * And compute size on demand:
 *   size = (rear - front + capacity) % capacity
 *
 ************************************************************
 * ✅ Intuition
 * ------------
 * - Dropping `count` saves one int field.
 * - We still need *both* `front` and `rear` indices because
 *   in a circular buffer they wrap independently.
 * - To tell empty from full we sacrifice one slot or use an
 *   explicit boolean flag.  Classic trick: keep one slot
 *   open; when rear is *about* to collide with front, we say
 *   “queue full”.
 *
 ************************************************************
 * ✅ Dry Run (capacity = 5, one slot sacrificed)
 * ----------------------------------------------
 *  Start         front=0 rear=0   → empty
 *  offer(10)     rear=1
 *  offer(20)     rear=2
 *  offer(30)     rear=3
 *  offer(40)     rear=4           (4 elems, one slot free)
 *  offer(50) ▸ full!  rear would be 0 == front
 *  poll() →10   front=1
 *  offer(50)    arr[4]=50 rear=0
 *  ...
 *
 *  size() at any point = (rear - front + cap) % cap
 *
 ************************************************************
 * ✅ Java Code (no `count`)
 * -------------------------
 */

public class ArrayQueueNoCount<T> {

    private final Object[] arr;
    private int front = 0;     // index of next element to poll
    private int rear = 0;     // index where next offer goes
    private final int capacity;

    public ArrayQueueNoCount(int capacity) {
        if (capacity < 2)        // need at least one spare slot
            throw new IllegalArgumentException("Capacity ≥ 2");
        this.capacity = capacity;
        this.arr = new Object[capacity];
    }

    public void offer(T data) {
        if (isFull())
            throw new IllegalStateException("Queue is full");
        arr[rear] = data;
        rear = (rear + 1) % capacity;
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        T value = (T) arr[front];
        arr[front] = null;            // help GC
        front = (front + 1) % capacity;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        return (T) arr[front];
    }

    public int size() {
        return (rear - front + capacity) % capacity;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;   // one slot rule
    }
}

/************************************************************
 * ✅ Complexity
 * ------------
 * Time: O(1) for all ops.
 * Space: O(n) for the array; no extra `count` int saved.
 *
 * ✅ Effectiveness
 * ----------------
 * • Eliminates one field → slightly smaller footprint.
 * • Logic a bit trickier (size formula, full detection).
 * • Advanced‑level pattern; many production circular queues
 *   use exactly this technique.
 ************************************************************/
