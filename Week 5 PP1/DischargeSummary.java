public class DischargeSummary {

    private String patientId;
    private String[] medicationCodes;

    static {
        System.out.println("Nightly Ledger Initialized");
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {

        this.patientId = patientId;

        for (String code : medicationCodes) {
            if (!isValidCode(code)) {
                throw new IllegalArgumentException("Invalid Medication Code");
            }
        }

        this.medicationCodes = medicationCodes.clone();
    }

    private boolean isValidCode(String code) {

        if (code == null || code.length() != 5)
            return false;

        if (!code.startsWith("MED-"))
            return false;

        char ch = code.charAt(4);

        return ch >= 'A' && ch <= 'Z';
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {

        String[] copy = medicationCodes.clone();
        copy[index] = newCode;

        return new DischargeSummary(patientId, copy);
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + critical + " critical-care | "
                + routine + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary[] summaries = {

                new CriticalCareDischargeSummary(
                        "MT001",
                        new String[]{"MED-X"},
                        4),

                null,

                new DischargeSummary(
                        "MT002",
                        new String[]{"MED-Y"})
        };

        System.out.println(
                processNightlyBatch(summaries));
    }
}

class CriticalCareDischargeSummary
        extends DischargeSummary {

    private int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}