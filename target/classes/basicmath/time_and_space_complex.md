✅ How to Analyze Your Code's Behavior (For Each Problem)
Here’s a systematic approach you can follow for each of your 100 problems:

🔹 1. Understand Growth with Input
Input Size (n)    What happens? Time Growth
Small (n=10)    How many iterations? Constant? Linear?
Medium (n=1e5)    Slow? Same speed? Noticeable delay?
Large (n=1e9)    Does it hang? Crash? Real bottleneck?

Use timers (like System.nanoTime() or System.currentTimeMillis()):

java
Copy
Edit
long start = System.currentTimeMillis();
isPrime(1_000_000_003); // Large input
long end = System.currentTimeMillis();
System.out.println("Execution time: " + (end - start) + " ms");
🔹 2. Log Internal Loops (Optional)
Track how many times a loop runs:

java
Copy
Edit
int count = 0;
for (int i = 1; i <= Math.sqrt(n); i++) {
if (n % i == 0) count++;
}
System.out.println("Loop ran " + count + " times");
This shows how performance scales with n.

🔹 3. Space Consumption?
Most of your current methods use O(1) space, but if you add arrays, lists, or maps later:

Monitor size of storage.

Count object creation if GC is involved.

🔹 4. Binary Search Behavior
For methods like squareRoot(n), test these cases:

n = 0, n = 1

n = perfect square

n = large non-square (e.g., 99999999)

Log low/mid/high to see binary search in action.

🔹 5. General Time Complexity Cheat Sheet
Function Typical Input Good to Test with
Count Digits int 0, -123, 1e9
Reverse Number int Palindromes, edge cases
GCD a, b Coprime, same, one is 0
isPrime int Primes, composites, huge numbers
isArmstrong int All 3-digit combos
squareRoot int 0, 1, perfect/non-perfect square
printDivisors int Primes, 1, squares, large values

🧠 Suggested Experiment
You could wrap your logic into a driver that runs each function with increasing inputs:

java
Copy
Edit
for (int i = 1; i <= 1_000_000; i *= 10) {
long start = System.nanoTime();
isPrime(i);
long end = System.nanoTime();
System.out.println("isPrime(" + i + ") took " + (end - start) + " ns");
}
This will visually show time growth.