package collections_practice.streams.exercise_1;

import java.util.List;

class Customer {
    private String customerId;
    private String name;
    private List<Order> orders;

    // Constructor
    public Customer(String customerId, String name, List<Order> orders) {
        this.customerId = customerId;
        this.name = name;
        this.orders = orders;
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // Optional: Override toString for better readability (for debugging)
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
