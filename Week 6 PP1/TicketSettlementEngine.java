class EventTicket {

    private static int counter = 1000;

    public final String ticketId;

    protected double basePrice;
    protected double paidAmount;

    public EventTicket(double basePrice) {

        counter++;

        ticketId = "TCK-" + counter;

        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        paidAmount += amount;
    }

    public void pay(double amount,
                    String mode) {

        System.out.println(
                "Payment Mode: "
                        + mode
        );

        pay(amount);
    }

    public double getBalanceDue() {
        return basePrice - paidAmount;
    }

    public static int getTicketsIssued() {
        return counter - 1000;
    }

    public static boolean isValidPromoCode(
            String code) {

        if (code.length() != 5)
            return false;

        if (code.charAt(0) != 'F')
            return false;

        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(
                    code.charAt(i)))
                return false;
        }

        return Character.isUpperCase(
                code.charAt(4));
    }
}

class GroupTicket extends EventTicket {

    private int groupSize;

    public GroupTicket(double basePrice,
                       int groupSize) {

        super(basePrice);

        this.groupSize = groupSize;
    }
}

public class TicketSettlementEngine {

    static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int skipped = 0;
        int groups = 0;
        int individuals = 0;

        for (EventTicket t : tickets) {

            if (t == null) {
                skipped++;
                continue;
            }

            processed++;

            if (t instanceof GroupTicket)
                groups++;
            else
                individuals++;
        }

        return processed
                + " processed | "
                + skipped
                + " null skipped | "
                + groups
                + " group | "
                + individuals
                + " individual";
    }

    public static void main(String[] args) {

        EventTicket t1 =
                new EventTicket(500);

        System.out.println(
                t1.ticketId
        );

        System.out.println(
                EventTicket.getTicketsIssued()
        );

        System.out.println(
                EventTicket.isValidPromoCode(
                        "F123A")
        );

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(
                t1.getBalanceDue()
        );

        EventTicket[] arr = {
                new GroupTicket(2000, 5),
                null,
                new EventTicket(500)
        };

        System.out.println(
                processNightlySettlement(arr)
        );
    }
}