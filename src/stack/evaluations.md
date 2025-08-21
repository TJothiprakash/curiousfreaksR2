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
🔹 Prefix (Polish Notation)

👉 Definition: Operator comes before operands.
Example:

* + 2 3 4


Equivalent to: (2 + 3) * 4

📝 Algorithm (Stack-based)

Scan prefix expression from right to left (because operator comes first).

If symbol is operand (number) → push it to stack.

If symbol is *operator (+, -, , /, ^) →

Pop two operands from stack.

Apply operator → result.

Push result back to stack.

At the end → stack top = final result.

🧮 Dry Run Example 1

Expression:

* + 2 3 4


Steps (scan right → left):

Read 4 → push → Stack: [4]

Read 3 → push → Stack: [4,3]

Read 2 → push → Stack: [4,3,2]

Read + → pop(2,3) → 2+3=5 → push → Stack: [4,5]

Read * → pop(5,4) → 5*4=20 → push → Stack: [20]

✅ Result = 20

🧮 Dry Run Example 2

Expression:

- * 5 2 4


Equivalent to: (5*2) - 4

Steps:

Scan right → left

Push 4 → [4]

Push 2 → [4,2]

Push 5 → [4,2,5]

Read * → pop(5,2) → 5*2=10 → push → [4,10]

Read - → pop(10,4) → 10-4=6 → push → [6]

✅ Result = 6

=====================================================================================================================
| Feature              | Infix                                     | Postfix / Prefix                              |
| -------------------- | ----------------------------------------- | --------------------------------------------- |
| Operator precedence  | **Required** (BODMAS)                     | **Not required**                              |
| Parentheses          | May be needed                             | **Not needed**                                |
| Evaluation direction | Left → Right, but follow precedence rules | Postfix: Left → Right<br>Prefix: Right → Left |
| Stack usage          | **Two stacks** (operands + operators)     | **One stack** (operands)                      |
| Complexity           | Slightly more complex                     | Simpler                                       |


🔹 Why Postfix & Prefix don’t need precedence

The order of operands and operators is already “baked in”.

No ambiguity exists, so you just scan and apply.

Example:

Infix: 2 + 3 * 4
- Need precedence → * first, then +

Postfix: 2 3 4 * +
- Just scan left → right
- Stack handles it automatically → 3*4 pushed, then +2

Prefix: + 2 * 3 4
- Scan right → left
- Stack automatically evaluates * first, then +2


✅ So in short:

In postfix/prefix, operator precedence disappears. You just iterate in order and let the stack handle evaluation.