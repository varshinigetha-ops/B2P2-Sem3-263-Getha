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

    public static String announceAll(RaceEntry[] entries) {

        StringBuilder report = new StringBuilder();

        for (RaceEntry entry : entries) {

            report.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entry;

                report.append(
                        " [Team size via downcast: "
                        + relay.getTeamSize()
                        + "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
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
    public String announce() {

        return "Runner Entry | Bib: " +
               getBibNumber() +
               " | Category: " +
               category +
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

    public int getTeamSize() {
        return teamSize;
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

public class RaceDayAnnouncerBoard {

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        90,
                        "Open 10K"
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        RaceEntry[] fleet = {
                runnerEntry,
                relayEntry
        };

        System.out.println(
                RaceEntry.announceAll(fleet)
        );
    }
}