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

    public String announce() {
        return "Race Entry | Bib: " +
                bibNumber +
                " | Balance: " +
                getBalanceDue();
    }

    public static String classifyGeneration(RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof RunnerEntry) {
            return "Direct child";
        }

        return "Base RaceEntry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {

        double total = 0;

        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
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

    @Override
    public String announce() {

        return "Runner Entry | Bib: " +
                getBibNumber() +
                " | Category: " +
                category +
                " | Balance: " +
                getBalanceDue();
    }
}

class EliteRunnerEntry extends RunnerEntry {

    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber,
                            double entryFee,
                            String category,
                            double sponsorBonus) {

        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {

        return "Elite Runner | Bib: " +
                getBibNumber() +
                " | Category: " +
                getCategory() +
                " | Sponsor Bonus: " +
                sponsorBonus +
                " | Balance: " +
                getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(String bibNumber,
                          double entryFee,
                          int teamSize) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public String announce() {

        return "Relay Team | Bib: " +
                getBibNumber() +
                " | Team Size: " +
                teamSize +
                " | Balance: " +
                getBalanceDue();
    }
}

public class RaceFamilyHierarchy {

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        EliteRunnerEntry eliteEntry =
                new EliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        System.out.println(runnerEntry.announce());
        System.out.println(eliteEntry.announce());
        System.out.println(relayEntry.announce());

        System.out.println(
                RaceEntry.classifyGeneration(eliteEntry)
        );

        System.out.println(
                RaceEntry.classifyGeneration(relayEntry)
        );

        RaceEntry[] entries = {
                runnerEntry,
                eliteEntry,
                relayEntry
        };

        System.out.println(
                RaceEntry.getTotalBalanceDue(entries)
        );
    }
}