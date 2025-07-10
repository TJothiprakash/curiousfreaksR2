| Implementation                  | `add()` / `offer()`                      | `remove()` / `poll()` | `peek()` / `element()` | Space |
| ------------------------------- | ---------------------------------------- | --------------------- | ---------------------- | ----- |
| **Array (Fixed Size)**          | O(1) (if space) / O(n) (if shift needed) | O(1)                  | O(1)                   | O(n)  |
| **Circular Array**              | O(1)                                     | O(1)                  | O(1)                   | O(n)  |
| **Dynamic Array (resizing)**    | Amortized O(1), worst O(n)               | O(1)                  | O(1)                   | O(n)  |
| **Linked List Queue**           | O(1)                                     | O(1)                  | O(1)                   | O(n)  |
| **Java’s `Queue` (LinkedList)** | O(1)                                     | O(1)                  | O(1)                   | O(n)  |
| **Java’s `ArrayDeque`**         | Amortized O(1)                           | Amortized O(1)        | O(1)                   | O(n)  |
| **PriorityQueue** (min-heap)    | O(log n)                                 | O(log n)              | O(1)                   | O(n)  |
| **Double-ended Queue (Deque)**  | O(1) (both ends)                         | O(1) (both ends)      | O(1)                   | O(n)  |



| **Method / Operation**       | **Time Complexity**                                       | **Space Complexity** | **Notes / Justification**                        |
| ---------------------------- | --------------------------------------------------------- | -------------------- | ------------------------------------------------ |
| `add()` (enqueue)            | 🟢 O(1) normally <br> 🔴 O(n) if resizing (dynamic array) | O(n) (array size)    | Resizing triggers copying all elements           |
| `remove()` (dequeue)         | 🟢 O(1)                                                   | O(n)                 | Just moves front pointer                         |
| `peek()`                     | 🟢 O(1)                                                   | O(n)                 | Just reads front element                         |
| Full-check inside `add()`    | 🔴 O(n) (if shifting in non-circular)                     | O(n)                 | Shifting required if array full and not circular |
| LinkedList `add()`           | 🟢 O(1)                                                   | O(n)                 | LinkedList grows dynamically                     |
| LinkedList `remove()`        | 🟢 O(1)                                                   | O(n)                 | Head pointer moves                               |
| Circular Queue `add()`       | 🟢 O(1) always                                            | O(n)                 | Wraps around, no resizing                        |
| Circular Queue `remove()`    | 🟢 O(1)                                                   | O(n)                 | Wrap-around handled using mod                    |
| PriorityQueue `add()`        | 🔶 O(log n)                                               | O(n)                 | Maintains heap property                          |
| PriorityQueue `remove()`     | 🔶 O(log n)                                               | O(n)                 | Heapify-down                                     |
| PriorityQueue `peek()`       | 🟢 O(1)                                                   | O(n)                 | Heap root access                                 |
| Deque `addFirst()/Last()`    | 🟢 O(1)                                                   | O(n)                 | Doubly-linked list                               |
| Deque `removeFirst()/Last()` | 🟢 O(1)                                                   | O(n)                 | Doubly-linked list                               |
