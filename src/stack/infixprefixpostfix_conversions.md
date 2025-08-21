🔹 Expression Example

We’ll use:

Infix: 2 + 3 * 4
Postfix: 2 3 4 * +
Prefix: + 2 * 3 4

🔹 Conversion Diagram
┌─────────┐
│ Infix │
│ 2 + 3*4 │
└─────────┘
│ │
Infix → │ │ Infix →
Postfix │ │ Prefix
2 3 4 * + │ │ + 2 * 3 4
│ │
▼ ▼
┌─────────┐ ┌─────────┐
│ Postfix │ │ Prefix │
│2 3 4 * +│ │+ 2 * 3 4│
└─────────┘ └─────────┘
│ ▲ │ ▲
│ │ │ │
Postfix→│ │Prefix→│
Infix │ │ Postfix
(2+(3*4))        2 3 4 * +

🔹 Step-by-Step Conversions (Text Form)

Infix → Postfix: 2 + 3 * 4 → 2 3 4 * +

Infix → Prefix: 2 + 3 * 4 → + 2 * 3 4

Postfix → Infix: 2 3 4 * + → (2 + (3 * 4))

Postfix → Prefix: 2 3 4 * + → + 2 * 3 4

Prefix → Infix: + 2 * 3 4 → (2 + (3 * 4))

Prefix → Postfix: + 2 * 3 4 → 2 3 4 * +

🔹 Key Notes

Postfix and Prefix are unambiguous → can be evaluated directly with a stack.

Infix requires precedence/parentheses → needs operator stack + operand stack.

Conversions often use a stack to keep operators/operands in order.

You can convert postfix ↔ prefix via infix as an intermediate, or directly with stacks.

=========================================================================================================================================================

🔹 1️⃣ Infix → Postfix
Intuition

Infix has operator precedence (+,-,*) and parentheses.

Postfix removes the need for precedence/parentheses — the order of operands and operators is baked in.

Use operator stack:

Push operators when they appear

Pop operators with higher/equal precedence to output

Parentheses control popping

Dry Run Example

Infix: (2 + 3) * 4

Symbol Stack Output
(    (()
2    (()    2

+ ((,+)    2
  3    ((,+)    2 3
  )    ()    2 3 +

* (*)    2 3 +
  4    (*)    2 3 + 4
  End    ()    2 3 + 4 *

✅ Postfix = 2 3 + 4 *

Java Code
static String infixToPostfix(String expr) {
StringBuilder output = new StringBuilder();
Stack<Character> ops = new Stack<>();

    for(int i=0; i<expr.length(); i++){
        char c = expr.charAt(i);
        if(c==' ') continue;
        if(Character.isDigit(c)){
            while(i<expr.length() && Character.isDigit(expr.charAt(i))){
                output.append(expr.charAt(i));
                i++;
            }
            i--;
            output.append(' ');
        } else if(c=='(') ops.push(c);
        else if(c==')'){
            while(ops.peek()!='(') output.append(ops.pop()).append(' ');
            ops.pop();
        } else if(isOperator(c)){
            while(!ops.isEmpty() && precedence(ops.peek())>=precedence(c))
                output.append(ops.pop()).append(' ');
            ops.push(c);
        }
    }
    while(!ops.isEmpty()) output.append(ops.pop()).append(' ');
    return output.toString().trim();

}

Complexity:

Time = O(n)

Space = O(n) (stack + output)

🔹 2️⃣ Infix → Prefix
Intuition

Prefix = operator before operands.

Trick: reverse infix + swap (↔) → convert to postfix → reverse → prefix

Dry Run Example

Infix: (2 + 3) * 4

Reverse & swap parentheses: 4 * (3 + 2)

Convert to postfix: 4 3 2 + *

Reverse → prefix: * + 2 3 4

✅ Prefix = * + 2 3 4

Java Code
static String infixToPrefix(String expr){
StringBuilder rev = new StringBuilder(expr).reverse();
for(int i=0;i<rev.length();i++){
if(rev.charAt(i)=='(') rev.setCharAt(i,')');
else if(rev.charAt(i)==')') rev.setCharAt(i,'(');
}
String postfix = infixToPostfix(rev.toString());
String[] tokens = postfix.split("\\s+");
StringBuilder prefix = new StringBuilder();
for(int i=tokens.length-1;i>=0;i--) prefix.append(tokens[i]).append(' ');
return prefix.toString().trim();
}

Complexity: O(n) time, O(n) space

🔹 3️⃣ Postfix → Infix
Intuition

Stack of strings (subexpressions).

Scan left → right:

Operand → push

Operator → pop two → combine (a op b) → push back

Dry Run Example

Postfix: 2 3 4 * +

Token Stack
2    [2]
3    [2,3]
4    [2,3,4]

* [2, (3*4)]

+ [(2 + (3*4))]

✅ Infix = (2 + (3*4))

Java Code
static String postfixToInfix(String expr){
Stack<String> stack = new Stack<>();
for(String token : expr.split("\\s+")){
if(isOperator(token.charAt(0)) && token.length()==1){
String b = stack.pop();
String a = stack.pop();
stack.push("(" + a + token + b + ")");
} else stack.push(token);
}
return stack.pop();
}

Complexity: O(n) time & space

🔹 4️⃣ Postfix → Prefix
Intuition

Similar stack method

Combine two operands → operator first (op a b) → push back

Dry Run

Postfix: 2 3 4 * +

Token Stack
2    [2]
3    [2,3]
4    [2,3,4]

* [2, * 3 4]

+ [+ 2 * 3 4]

✅ Prefix = + 2 * 3 4

Java Code
static String postfixToPrefix(String expr){
Stack<String> stack = new Stack<>();
for(String token : expr.split("\\s+")){
if(isOperator(token.charAt(0)) && token.length()==1){
String b = stack.pop();
String a = stack.pop();
stack.push(token + " " + a + " " + b);
} else stack.push(token);
}
return stack.pop();
}

Complexity: O(n)

🔹 5️⃣ Prefix → Infix
Intuition

Stack of strings

Scan right → left

Operand → push

Operator → pop two → combine (a op b) → push

Dry Run

Prefix: * + 2 3 4

Token Stack
4    [4]
3    [4,3]
2    [4,3,2]

+ [4, (2+3)]

* [(2+3)*4]

✅ Infix = (2+3)*4

Java Code
static String prefixToInfix(String expr){
Stack<String> stack = new Stack<>();
String[] tokens = expr.split("\\s+");
for(int i=tokens.length-1;i>=0;i--){
String token = tokens[i];
if(isOperator(token.charAt(0)) && token.length()==1){
String a = stack.pop();
String b = stack.pop();
stack.push("(" + a + token + b + ")");
} else stack.push(token);
}
return stack.pop();
}

Complexity: O(n)

🔹 6️⃣ Prefix → Postfix
Intuition

Scan right → left

Stack of strings

Operator → pop two operands → combine a b op → push back

Dry Run

Prefix: * + 2 3 4

Token Stack
4    [4]
3    [4,3]
2    [4,3,2]

+ [4, 2 3 +]

* [2 3 + 4 *]

✅ Postfix = 2 3 + 4 *

Java Code
static String prefixToPostfix(String expr){
Stack<String> stack = new Stack<>();
String[] tokens = expr.split("\\s+");
for(int i=tokens.length-1;i>=0;i--){
String token = tokens[i];
if(isOperator(token.charAt(0)) && token.length()==1){
String a = stack.pop();
String b = stack.pop();
stack.push(a + " " + b + " " + token);
} else stack.push(token);
}
return stack.pop();
}

Complexity: O(n)

✅ Summary Table of Conversions

From \ To Infix Postfix Prefix
Infix - ✅ ✅
Postfix ✅ - ✅
Prefix ✅ ✅ -

All use stack of operands/operators and O(n) time & space.