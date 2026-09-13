class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0)
            amountPaid += amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccountInheritance {

    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("RA101", 150000, 150000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("RA102", 200000, 60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA103",
                        180000, 0, 20);

        System.out.println("Plain account due: Rs "
                + plain.getDue());

        System.out.println("Hostel account due: Rs "
                + hostel.getDue());

        System.out.println("Scholarship account effective due: Rs "
                + scholarship.effectiveDue());
    }
}