🔹 Infix Expression Evaluation Using Stacks

👉 Idea:
Since infix requires precedence and parentheses, we use two stacks:

Operand Stack → stores numbers.

Operator Stack → stores operators (+, -, *, /, ^, …).

📝 Algorithm (Shunting Yard Evaluation Style)

Scan expression from left to right.

If the current symbol is:

Operand (number) → push it into operand stack.

Operator (+,-,*,/) →

While top of operator stack has higher/equal precedence,
pop it → pop 2 operands → apply operator → push result back.

Then push the current operator.

Left parenthesis ( → push to operator stack.

Right parenthesis ) → pop operators until a ( is found.

At the end → apply remaining operators until only one value is left in operand stack.

✅ That final value = result.

🧮 Dry Run Example 1

Expression:

2 + 3 * 4


Steps:

Read 2 → operand → push → Operand Stack: [2]

Read + → Operator Stack: [+]

Read 3 → Operand Stack: [2,3]

Read * → higher precedence than + → push → Operator Stack: [+,*]

Read 4 → Operand Stack: [2,3,4]

End → pop * → apply 3*4=12 → Operand Stack: [2,12]

Pop + → apply 2+12=14 → Operand Stack: [14]

✅ Result = 14

🧮 Dry Run Example 2

Expression:

(2 + 3) * 4


Steps:

Read ( → Operator Stack: [(]

Read 2 → Operand Stack: [2]

Read + → Operator Stack: [(, +]

Read 3 → Operand Stack: [2,3]

Read ) → pop until ( → Apply 2+3=5 → Operand Stack: [5]

Read * → Operator Stack: [*]

Read 4 → Operand Stack: [5,4]

End → pop * → apply 5*4=20 → Operand Stack: [20]

✅ Result = 20

=========================================================================================================
🔹 Postfix (Reverse Polish Notation)

👉 Definition: Operator comes after operands.
Example:

2 3 + 4 *


This means: (2 + 3) * 4

📝 Algorithm (Stack-based)

Scan postfix expression from left to right.

If the symbol is an operand (number) → push it to stack.

If the symbol is an *operator (+, -, , /, ^) →

Pop the top two operands from stack.

Apply the operator → result.

Push result back to stack.

At the end, stack top = final result.

🧮 Dry Run Example 1

Expression:

2 3 + 4 *


Steps:

Push 2 → Stack: [2]

Push 3 → Stack: [2,3]

+ → Pop(3,2) → 2+3=5 → Push 5 → Stack: [5]

Push 4 → Stack: [5,4]

* → Pop(4,5) → 5*4=20 → Push 20 → Stack: [20]

✅ Result = 20

🧮 Dry Run Example 2

Expression:

5 2 - 4 *


Equivalent to (5 - 2) * 4

Steps:

Push 5 → [5]

Push 2 → [5,2]

- → Pop(2,5) → 5-2=3 → [3]

Push 4 → [3,4]

* → Pop(4,3) → 3*4=12 → [12]

✅ Result = 12

=================================================================================================================
