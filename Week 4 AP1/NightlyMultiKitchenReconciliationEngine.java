class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static {

        System.out.println(
                "Delivery System Initialized");
    }

    public DeliveryAccount(
            String studentId,
            double orderValue) {

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(
            String studentId) {

        this(studentId,0);
    }

    public final double calculateSurgeFee(
            int delayMinutes) {

        return orderValue *
                0.01 *
                delayMinutes;
    }
}

class PremiumAccount
        extends DeliveryAccount {

    public PremiumAccount(
            String studentId,
            double orderValue) {

        super(studentId,
              orderValue);
    }
}

public class NightlyMultiKitchenReconciliationEngine {

    public static void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if(account == null)
            return;

        double fee =
                account.calculateSurgeFee(
                        delayMinutes);

        System.out.println(
                account.studentId +
                " Fee = " + fee);
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if(accounts.length
                != amounts.length ||
           accounts.length
                != delayMinutesArray.length) {

            System.out.println(
                    "Array size mismatch");
            return;
        }

        int processed = 0;
        int skipped = 0;
        int premium = 0;
        int regular = 0;

        double grandTotal = 0;

        for(int i=0;
            i<accounts.length;
            i++) {

            if(accounts[i] == null) {

                skipped++;
                continue;
            }

            processed++;

            if(accounts[i]
                    instanceof PremiumAccount)
                premium++;
            else
                regular++;

            grandTotal +=
                    accounts[i]
                    .calculateSurgeFee(
                            delayMinutesArray[i]);
        }

        System.out.println(
                processed +
                " processed | " +
                skipped +
                " null skipped | " +
                premium +
                " premium | " +
                regular +
                " regular | grand total surge fees = "
                + grandTotal);
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {

                new PremiumAccount(
                        "STU001",
                        500),

                null,

                new DeliveryAccount(
                        "STU002",
                        300)
        };

        double[] amounts = {
                500,400,300
        };

        int[] delays = {
                10,5,0
        };

        processBatch(
                accounts,
                amounts,
                delays);
    }
}