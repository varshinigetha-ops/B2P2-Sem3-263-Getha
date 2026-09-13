public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Constructor 1
    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0)
            throw new IllegalArgumentException("Fare cannot be negative");

        if (passengerCount <= 0)
            throw new IllegalArgumentException("Passenger count must be positive");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor 2
    public FareSplitter(String tripId,
                        double totalFare) {
        this(tripId, totalFare, 2);
    }

    // Constructor 3
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] shares = new double[passengerCount];

        if (totalFare == 0)
            return shares;

        double baseShare =
                Math.floor((totalFare / passengerCount) * 100) / 100.0;

        for (int i = 0; i < passengerCount; i++) {
            shares[i] = baseShare;
        }

        double usedAmount = baseShare * passengerCount;

        double remainder =
                Math.round((totalFare - usedAmount) * 100) / 100.0;

        shares[passengerCount - 1] += remainder;

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed,
                                         int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter trip1 =
                new FareSplitter("TRIP001",
                        100000,
                        3);

        double[] result1 =
                trip1.fareBreakdown();

        System.out.print("Trip1: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i]);
            if (i != result1.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        FareSplitter trip2 =
                new FareSplitter("TRIP003");

        double[] result2 =
                trip2.fareBreakdown();

        System.out.print("Trip2: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
            if (i != result2.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        System.out.println("Overdue: "
                + trip1.isConfirmationOverdue(2, 3));
    }
}
