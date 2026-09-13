public class MembershipFieldReachChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {

        switch (fieldModifier.toLowerCase()) {

            case "private":
                return accessorContext.equals("SAME_CLASS")
                        ? "ALLOWED" : "DENIED";

            case "default":
                return (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "protected":
                return (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE"))
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String result = classifyAccess(modifier, attempt[1]);

            if (modifier.equalsIgnoreCase("private")) {
                if (result.equals("ALLOWED"))
                    privateAllowed++;
                else
                    privateDenied++;
            }

            else if (modifier.equalsIgnoreCase("default")) {
                if (result.equals("ALLOWED"))
                    defaultAllowed++;
                else
                    defaultDenied++;
            }

            else if (modifier.equalsIgnoreCase("protected")) {
                if (result.equals("ALLOWED"))
                    protectedAllowed++;
                else
                    protectedDenied++;
            }

            else if (modifier.equalsIgnoreCase("public")) {
                if (result.equals("ALLOWED"))
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }

        return "private: " + privateAllowed + " allowed / " + privateDenied + " denied"
                + " | default: " + defaultAllowed + " allowed / " + defaultDenied + " denied"
                + " | protected: " + protectedAllowed + " allowed / " + protectedDenied + " denied"
                + " | public: " + publicAllowed + " allowed / " + publicDenied + " denied";
    }

    public static void main(String[] args) {

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                summarizeByModifier(attempts));

        LibraryMember member =
                new LibraryMember(
                        "LB94",
                        "BR1",
                        0,
                        "Priya Nair");

        System.out.println("Member Created Successfully");
    }
}

class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {

        if (membershipId == null
                || membershipId.trim().isEmpty()
                || membershipId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid Membership ID");
        }

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}