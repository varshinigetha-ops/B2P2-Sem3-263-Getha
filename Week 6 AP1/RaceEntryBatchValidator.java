class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException("Invalid Bib Number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public static String registerBatch(String[] bibNumbers, double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {

            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
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

    public String getCategory() {
        return category;
    }
}

public class RaceEntryBatchValidator {

    public static void main(String[] args) {

        try {
            RaceEntry r1 = new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        System.out.println(r.getBalanceDue());

        String[] bibs = {
                "BIB1",
                "B1",
                "BIB2"
        };

        System.out.println(
                RaceEntry.registerBatch(bibs, 80)
        );
    }
}