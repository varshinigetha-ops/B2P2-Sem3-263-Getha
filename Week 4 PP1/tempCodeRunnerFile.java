public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Invalid input");
        }

        if (minutesLate == 0) {
            return 0;
        }

        double penalty = 0;

        if (minutesLate <= 5) {

            penalty = minutesLate *
                    (ticketFare * 0.005);

        } else if (minutesLate <= 15) {

            penalty =
                    (5 * ticketFare * 0.005) +
                    ((minutesLate - 5) *
                            ticketFare * 0.01);

        } else {

            penalty =
                    (5 * ticketFare * 0.005) +
                    (10 * ticketFare * 0.01) +
                    ((minutesLate - 15) *
                            ticketFare * 0.02);
        }

        double minimumPenalty =
                ticketFare *
                        minimumPenaltyPercent / 100;

        return Math.max(penalty, minimumPenalty);
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1);

        System.out.println(
                calculator.calculatePenalty(
                        1000, 0));

        System.out.println(
                calculator.calculatePenalty(
                        1000, 1));

        System.out.println(
                calculator.calculatePenalty(
                        1000, 16));
    }
}