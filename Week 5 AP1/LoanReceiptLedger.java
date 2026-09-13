public class LoanReceiptLedger {

    static {
        System.out.println("Nightly Circulation Ledger Started");
    }

    public static void main(String[] args) {

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }

    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt r : receipts) {

            if (r == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (r instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        if (memberId == null ||
                memberId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Invalid Member ID");
        }

        for (String id : bookIds) {

            if (!id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException(
                        "Invalid Book ID");
            }
        }

        this.memberId = memberId;

        this.bookIds = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            this.bookIds[i] = bookIds[i];
        }
    }

    public String[] getBookIds() {

        String[] copy = new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {
            copy[i] = bookIds[i];
        }

        return copy;
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (!newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException(
                    "Invalid Book ID");
        }

        String[] copy = getBookIds();

        if (index >= 0 &&
                index < copy.length) {

            copy[index] = newId;
        }

        return new LoanReceipt(
                memberId,
                copy
        );
    }
}

class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}