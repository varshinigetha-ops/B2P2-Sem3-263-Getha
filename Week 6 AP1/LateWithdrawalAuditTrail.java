import java.util.Arrays;

class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double amountPaid;

    private double[] lateFeeHistory;
    private int feeCount;

    public RaceEntry(String bibNumber,
                     double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException("Invalid Bib Number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        feeCount = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {

        double totalLateFees = 0;

        for (int i = 0; i < feeCount; i++) {
            totalLateFees += lateFeeHistory[i];
        }

        return entryFee - amountPaid + totalLateFees;
    }

    protected void applyLateFee(double amount) {

        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalAuditTrail {

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(
                r.getBalanceDue()
        );

        double[] history =
                r.getLateFeeHistory();

        System.out.println(
                Arrays.toString(history)
        );

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        r.getLateFeeHistory()
                )
        );
    }
}