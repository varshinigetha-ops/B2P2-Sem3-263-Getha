class FixedStudent {

    String name;
    String regNo;
    int attendance;

    static String university = "SRM";
    static int admissionCount = 0;

    FixedStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: "
                + admissionCount);
    }
}

public class StaticVsInstanceDemo {

    public static void main(String[] args) {

        FixedStudent s1 =
                new FixedStudent("Ravi", 82);

        FixedStudent s2 =
                new FixedStudent("Meera", 91);

        s1.printIdCard();
        s2.printIdCard();

        FixedStudent.printTotalAdmissions();
    }
}
