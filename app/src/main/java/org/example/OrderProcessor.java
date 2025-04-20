package org.example;
import java.util.List;

public class OrderProcessor {

    public void printOrderSummary(Order order) {
        double totalPrice = calculateTotalPrice(order);

        System.out.println("Order Summary:");
        System.out.println("Customer: " + order.getCustomer().getName());
        printItems(order);
        System.out.printf("Total Price: $%.2f%n", totalPrice);
    }

    private double calculateTotalPrice(Order order) {
        double total = 0;
        for (Item item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }

        if (order.getCustomer().isMember()) {
            total *= 0.9; // Apply 10% discount
        }

        return total;
    }

    private void printItems(Order order) {
        System.out.println("Items:");
        for (Item item : order.getItems()) {
            double subtotal = item.getQuantity() * item.getPrice();
            System.out.printf("  - %s: %d x $%.2f = $%.2f%n",
                item.getName(), item.getQuantity(), item.getPrice(), subtotal);
        }
    }
}


class Customer {
    private String name;
    private boolean isMember;

    public Customer(String name, boolean isMember) {
        this.name = name;
        this.isMember = isMember;
    }

    public String getName() { return name; }
    public boolean isMember() { return isMember; }
}

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}

class Order {
    private Customer customer;
    private List<Item> items;

    public Order(Customer customer, List<Item> items) {
        this.customer = customer;
        this.items = items;
    }

    public Customer getCustomer() { return customer; }
    public List<Item> getItems() { return items; }
}
