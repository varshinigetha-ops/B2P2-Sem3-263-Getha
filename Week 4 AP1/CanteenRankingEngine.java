class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode,
                   String canteenName,
                   int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode,
                   String canteenName) {

        this(canteenCode,canteenName,3);
    }

    public int compareTo(Canteen other) {

        if(this.trustScore != other.trustScore)
            return other.trustScore - this.trustScore;

        return this.canteenCode.compareToIgnoreCase(
                other.canteenCode);
    }

    public String getCode() {
        return canteenCode;
    }

    public static Canteen[] rankCanteens(
            Canteen[] canteens) {

        for(int i=0;i<canteens.length-1;i++) {

            for(int j=0;
                j<canteens.length-i-1;
                j++) {

                if(canteens[j].compareTo(
                        canteens[j+1]) > 0) {

                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j+1];
                    canteens[j+1] = temp;
                }
            }
        }

        return canteens;
    }
}

public class CanteenRankingEngine {

    public static void main(String[] args) {

        Canteen[] list = {

                new Canteen(
                        "HB3-C",
                        "Spice Junction",
                        3),

                new Canteen(
                        "hb1-c",
                        "Grand Mess",
                        5),

                new Canteen(
                        "HB2-C",
                        "Southern Treats")
        };

        Canteen.rankCanteens(list);

        for(Canteen c : list) {
            System.out.println(c.getCode());
        }
    }
}
