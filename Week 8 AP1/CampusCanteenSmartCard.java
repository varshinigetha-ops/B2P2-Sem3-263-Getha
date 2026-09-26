import java.util.*;

interface PricingPlan {
    double calculatePrice(double price);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price;
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.80;
    }
}

class Transaction {
    String description;
    double amount;

    Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }
}

class Purchase {
    String item;
    double amount;
    boolean refunded = false;

    Purchase(String item, double amount) {
        this.item = item;
        this.amount = amount;
    }
}

class SmartCard {
    private String cardNumber;
    private double balance = 0;
    private boolean blocked = false;
    private PricingPlan plan;
    private List<Transaction> transactions =
            new ArrayList<>();
    private List<Purchase> purchases =
            new ArrayList<>();

    SmartCard(String cardNumber, PricingPlan plan) {
        this.cardNumber = cardNumber;
        this.plan = plan;
    }

    public void topUp(double amount) {

        if (blocked) {
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println(
                    "Top-up failed: Minimum top-up is ₹100.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println(
                    "Top-up failed: Maximum balance is ₹5000.");
            return;
        }

        balance += amount;

        transactions.add(
                new Transaction("Top-up", amount));

        System.out.printf(
                "%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                cardNumber, amount, balance);
    }

    public Purchase purchase(String item, double price) {

        if (blocked) {
            System.out.println(
                    "Purchase failed: Card is blocked.");
            return null;
        }

        double charged = plan.calculatePrice(price);

        if (charged > balance) {
            System.out.printf(
                    "Purchase failed: Insufficient balance " +
                    "(required ₹%.2f, available ₹%.2f).%n",
                    charged, balance);
            return null;
        }

        balance -= charged;

        Purchase purchase =
                new Purchase(item, charged);

        purchases.add(purchase);

        transactions.add(
                new Transaction(item, -charged));

        System.out.printf(
                "%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                item, charged, balance);

        return purchase;
    }

    public void refund(Purchase purchase) {

        if (purchase == null)
            return;

        if (purchase.refunded) {
            System.out.println(
                    "Refund rejected: " +
                    purchase.item +
                    " has already been refunded.");
            return;
        }

        balance += purchase.amount;
        purchase.refunded = true;

        transactions.add(
                new Transaction(
                        "Refund " + purchase.item,
                        purchase.amount));

        System.out.printf(
                "Refund of ₹%.2f for %s processed. " +
                "Balance: ₹%.2f.%n",
                purchase.amount,
                purchase.item,
                balance);
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    public void statement() {

        System.out.print(
                "Mini-statement for " +
                cardNumber + ": ");

        for (int i = 0; i < transactions.size(); i++) {

            double amount = transactions.get(i).amount;

            if (amount >= 0)
                System.out.printf("+%.2f", amount);
            else
                System.out.printf("%.2f", amount);

            if (i < transactions.size() - 1)
                System.out.print(", ");
        }

        System.out.printf(
                " = ₹%.2f%n", balance);
    }
}

public class CampusCanteenSmartCard {

    public static void main(String[] args) {

        SmartCard card =
                new SmartCard(
                        "C-2045",
                        new HostellerPlan());

        card.topUp(500);

        Purchase thali =
                card.purchase("Veg Thali", 120);

        card.purchase("Cold Coffee", 60);

        card.purchase("Items", 400);

        card.refund(thali);

        card.refund(thali);

        card.statement();
    }
}