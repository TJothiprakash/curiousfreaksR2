package collections_practice.streams.exercise_1;

import java.time.LocalDate;

class Order {
    private String orderId;
    private double amount;
    private String status; // "DELIVERED", "PENDING", "CANCELLED"
    private LocalDate orderDate;

    public Order(String orderId, double amount, String status, LocalDate orderDate) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
