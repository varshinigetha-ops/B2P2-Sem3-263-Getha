class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;

    static {
        System.out.println(
                "Bus Ticket System Initialized");
    }

    public BusTicketAccount(
            String bookingId,
            double ticketFare) {

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(
            String bookingId) {

        this(bookingId, 0);
    }

    public final double calculatePenalty(
            int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException();
        }

        return ticketFare *
                0.01 * minutesLate;
    }
}

class SleeperCoachAccount
        extends BusTicketAccount {

    public SleeperCoachAccount(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }
}

public class NightlyFleetReconciliationEngine {

    public static void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {
            return;
        }

        double penalty =
                account.calculatePenalty(
                        minutesLate);

        System.out.println(
                account.bookingId +
                        " Penalty = " +
                        penalty);
    }

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts.length != amounts.length
                || accounts.length != minutesLateArray.length) {

            System.out.println(
                    "Array size mismatch");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;

        double grandTotal = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (accounts[i]
                    instanceof SleeperCoachAccount) {
                sleeper++;
            } else {
                regular++;
            }

            double penalty =
                    accounts[i].calculatePenalty(
                            minutesLateArray[i]);

            grandTotal += penalty;
        }

        System.out.println(
                processed +
                        " processed | "
                        + nullSkipped +
                        " null skipped | "
                        + sleeper +
                        " sleeper | "
                        + regular +
                        " regular | grand total penalties = "
                        + grandTotal);
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

                new SleeperCoachAccount(
                        "BK001", 2000),

                null,

                new BusTicketAccount(
                        "BK002", 1200)
        };

        double[] amounts = {
                1200, 900, 700
        };

        int[] late = {
                10, 5, 0
        };

        processBatch(
                accounts,
                amounts,
                late);
    }
}