import java.util.Arrays;

class EventTicket {

    protected double basePrice;
    protected double paidAmount;

    private double[] history = new double[10];
    private int count = 0;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        paidAmount += amount;
    }

    protected void applyLateFee(double amount) {
        basePrice += amount;
        history[count++] = amount;
    }

    public double getBalanceDue() {
        return basePrice - paidAmount;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(history, count);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class LateFeeAuditTrail {

    public static void main(String[] args) {

        WorkshopTicket w =
                new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history =
                w.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        w.getLateFeeHistory()
                )
        );
    }
}