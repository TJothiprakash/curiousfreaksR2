package lld.splitexpensesharingsystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
}
 class SplitwiseDemo {
    public static void main(String[] args) {
        Customer u1 = new Customer("u1", "Alice");
        Customer u2 = new Customer("u2", "Bob");
        Customer u3 = new Customer("u3", "Charlie");

        List<Customer> group = Arrays.asList(u1, u2, u3);

        Expense e1 = new Expense(u1, 300.0, group, new EqualSplits());
        e1.applySplit();

        System.out.println("Balances:");
        System.out.println("Alice -> " + u1.balanceSheet);
        System.out.println("Bob -> " + u2.balanceSheet);
        System.out.println("Charlie -> " + u3.balanceSheet);
    }
}
interface splitStrategy {
    void shareExpense(Expense expense);
}

class EqualSplits implements splitStrategy {
    @Override
    public void shareExpense(Expense expense) {
        int numUsers = expense.participants.size();
        double share = expense.amount / numUsers;

        for (Customer user : expense.participants) {
            if (!user.id.equals(expense.paidBy.id)) {
                // user owes `share` to paidBy
                user.addBalance(expense.paidBy.id, -share);
                expense.paidBy.addBalance(user.id, share);
            }
        }
    }
}

class Expense {
    Customer paidBy;
    double amount;
    List<Customer> participants;
    splitStrategy strategy;

    public Expense(Customer paidBy, double amount, List<Customer> participants, splitStrategy strategy) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.participants = participants;
        this.strategy = strategy;
    }

    public void applySplit() {
        strategy.shareExpense(this);
    }
}
class Customer {
    String id;
    String name;
    Map<String, Double> balanceSheet = new HashMap<>(); // otherUserId -> amount owed (+/-)

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBalance(String userId, double amount) {
        balanceSheet.put(userId, balanceSheet.getOrDefault(userId, 0.0) + amount);
    }
}
