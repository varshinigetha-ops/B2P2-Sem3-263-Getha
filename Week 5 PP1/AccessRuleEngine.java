public class AccessRuleEngine {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
            case "protected":
                return (accessorContext.equals("SAME_CLASS") ||
                        accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;

        for (String[] row : attempts) {
            if (classifyAccess(row[0], row[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        String[][] attempts = {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));

        try {
            PatientRecord p =
                    new PatientRecord("MT94", "W3", 98.2, "MediTrack");
            System.out.println("Patient Record Created");
        } catch (IllegalArgumentException e) {
            System.out.println("Construction Rejected");
        }
    }
}

class PatientRecord {
    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {

        if (patientId == null ||
                patientId.trim().isEmpty() ||
                patientId.trim().length() < 4) {
            throw new IllegalArgumentException();
        }

        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}