import java.util.*;

interface IPaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements IPaymentMethod {

    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {

    public boolean pay(double amount) {
        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class CashOnDelivery implements IPaymentMethod {

    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Cash on Delivery";
    }
}

class FoodItem {
    private String name;
    private double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class LineItem {
    private FoodItem item;
    private int quantity;

    LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.getPrice() * quantity;
    }

    public String getName() {
        return item.getName();
    }

    public int getQuantity() {
        return quantity;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {
    private static int nextId = 123;

    private int orderId;
    private Customer customer;
    private List<LineItem> items = new ArrayList<>();
    private String status = "Created";

    Order(Customer customer) {
        this.customer = customer;
        this.orderId = nextId++;
        System.out.println("Order created.");
    }

    public void addItem(FoodItem item, int quantity) {
        items.add(new LineItem(item, quantity));

        System.out.println("Added " +
                item.getName() +
                " (Qty " + quantity + ")");
    }

    public void placeOrder(IPaymentMethod paymentMethod) {

        if (items.isEmpty()) {
            System.out.println(
                    "Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");

        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        boolean success = paymentMethod.pay(total);

        if (success) {
            status = "Paid";

            System.out.println(
                    "Payment via " +
                    paymentMethod.getName() +
                    " successful.");

            System.out.println(
                    "Order status: Paid.");

            System.out.println(
                    "Notification: Order #" +
                    orderId +
                    " placed and paid.");
        } else {
            status = "Pending Payment";

            System.out.println(
                    "Payment via " +
                    paymentMethod.getName() +
                    " failed.");

            System.out.println(
                    "Order status: Pending Payment.");

            System.out.println(
                    "Notification: Order #" +
                    orderId +
                    " placed, awaiting payment.");
        }
    }
}

public class FoodOrderSystem {
    public static void main(String[] args) {

        Customer customer = new Customer("John");

        FoodItem pizza = new FoodItem("Pizza", 200);
        FoodItem soda = new FoodItem("Soda", 50);
        FoodItem burger = new FoodItem("Burger", 150);

        Order order1 = new Order(customer);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        order1.placeOrder(new CreditCardPayment());

        Order order2 = new Order(customer);

        order2.addItem(burger, 1);

        order2.placeOrder(new DigitalWalletPayment());
    }
}