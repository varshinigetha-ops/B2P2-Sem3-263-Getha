class HostelFeeAccount {

    double totalFee;
    double amountPaid;

    HostelFeeAccount(double totalFee) {
        this.totalFee = totalFee;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
        else
            System.out.println("Payment Rejected");
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelRoom {

    String roomNo;

    HostelRoom(String roomNo) {
        this.roomNo = roomNo;
    }
}

class SrmStudent {

    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount,
               HostelRoom room) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;

        totalStudents++;
    }

    void fullStatus() {

        String roomNumber =
                (room == null) ? "unallotted" : room.roomNo;

        System.out.println(name +
                " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " +
                roomNumber);
    }
}

public class FeeHostelManagementSystem {

    public static void main(String[] args) {

        HostelRoom r1 = new HostelRoom("C-214");
        HostelRoom r2 = new HostelRoom("C-507");

        HostelFeeAccount f1 =
                new HostelFeeAccount(200000);

        HostelFeeAccount f2 =
                new HostelFeeAccount(180000);

        HostelFeeAccount f3 =
                new HostelFeeAccount(200000);

        f1.pay(60000);
        f2.pay(0);
        f3.pay(-1000);

        SrmStudent s1 =
                new SrmStudent("Ravi",
                        "RA101", f1, r1);

        SrmStudent s2 =
                new SrmStudent("Anitha",
                        "RA102", f2, r2);

        SrmStudent s3 =
                new SrmStudent("Karthik",
                        "RA103", f3, null);

        s1.fullStatus();
        s2.fullStatus();
        s3.fullStatus();

        System.out.println("Total Students: "
                + SrmStudent.totalStudents);
    }
}
