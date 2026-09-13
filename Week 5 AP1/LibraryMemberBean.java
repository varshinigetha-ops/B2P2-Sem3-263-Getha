public class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // No-arg constructor
    public LibraryMemberBean() {
        this(null, null);
    }

    // Name-only constructor
    public LibraryMemberBean(String name) {
        this(null, name);
    }

    // ID + Name constructor
    public LibraryMemberBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
    }

    // Getter
    public String getMembershipId() {
        return membershipId;
    }

    // Write-once setter
    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Boolean JavaBean methods
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only property
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswer =
                    Integer.toString(answer.hashCode());
        }
    }

    public static void main(String[] args) {

        LibraryMemberBean m = new LibraryMemberBean();

        m.setMembershipId("LIB-8841");
        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        LibraryMemberBean m2 =
                new LibraryMemberBean("LIB-9001",
                        "Priya Nair");

        System.out.println(m2.getMembershipId());

        m2.setPremiumMember(true);

        System.out.println(m2.isPremiumMember());
    }
}