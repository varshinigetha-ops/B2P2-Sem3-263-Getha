public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(
            double minimumSurgePercent) {

        this.minimumSurgePercent =
                minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        if(orderValue < 0 ||
           delayMinutes < 0)
            throw new IllegalArgumentException();

        if(delayMinutes == 0)
            return 0;

        double fee = 0;

        if(delayMinutes <= 5) {

            fee = delayMinutes *
                    orderValue * 0.005;

        } else if(delayMinutes <= 15) {

            fee =
                    (5 * orderValue * 0.005)
                    +
                    ((delayMinutes-5)
                     * orderValue * 0.01);

        } else {

            fee =
                    (5 * orderValue * 0.005)
                    +
                    (10 * orderValue * 0.01)
                    +
                    ((delayMinutes-15)
                     * orderValue * 0.02);
        }

        double minimumFee =
                orderValue *
                minimumSurgePercent / 100;

        return Math.max(fee,
                minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator s =
                new SurgeFeeCalculator(1);

        System.out.println(
                s.calculateSurgeFee(
                        500,0));

        System.out.println(
                s.calculateSurgeFee(
                        500,1));

        System.out.println(
                s.calculateSurgeFee(
                        500,16));
    }
}