class LibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName =
            "SRM Library";

    static int memberCount = 1000;

    LibraryMember(String name,
                  int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId =
                "LM-" + memberCount;
    }

    void printMemberCard() {
        System.out.println(
                name + " | "
                        + memberId);
    }

    static void printTotalMembers() {
        System.out.println(
                "Total members: "
                        + (memberCount - 1000));
    }
}

public class LibraryMembershipSystem {

    public static void main(String[] args) {

        LibraryMember m1 =
                new LibraryMember(
                        "Aditi", 2);

        LibraryMember m2 =
                new LibraryMember(
                        "Rohan", 4);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember
                .printTotalMembers();
    }
}
