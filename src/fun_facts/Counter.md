Use javap (the Java bytecode disassembler)
1. Create this simple class:
  
   public class Counter {
   int count = 0;

   void increment() {
    count++;
}

   }



2. Compile it:
   bash
   Copy
   Edit
   javac Counter.java
3. Run this command:
   bash
   Copy
   Edit
   javap -c Counter
   👀 Output:
   csharp
   Copy
   Edit
   Compiled from "Counter.java"
   public class Counter {
   int count;

public Counter();
Code:
0: aload_0
1: invokespecial #1   // Method java/lang/Object."<init>":()V
4: return

void increment();
Code:
0: aload_0         // Load 'this'
1: dup
2: getfield      #2   // Fetch 'count'
5: iconst_1
6: iadd
7: putfield      #2   // Set 'count' = count + 1
10: return
}
🔍 Breakdown of Bytecode:
Instruction	Meaning
aload_0	load this onto the stack
dup	duplicate the top value of the stack (needed to do get and put on same object)
getfield	fetch the value of count
iconst_1	load constant 1
iadd	add 1 to count
putfield	store the result back into count

These bytecode operations prove that count++ is not atomic — the JVM has to:

Load the object and field

Increment

Store the result back

If another thread sneaks in between those steps... boom 💥: race condition.

✅ Summary:
Yes, count++ is syntactic sugar — the real machine instructions or JVM bytecode break it into read → compute → write, and these are not atomic unless synchronized.

