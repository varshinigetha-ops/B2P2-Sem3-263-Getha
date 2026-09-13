class Employee {
    double salary;

    Employee(double salary) {
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee
        extends Employee {

    double bonus;

    ManagerEmployee(
            double salary,
            double bonus) {

        super(salary);
        this.bonus = bonus;
    }

    double effectiveSalary() {
        return salary + bonus;
    }
}

class ParkingSlot {

    String slotNo;

    ParkingSlot(String slotNo) {
        this.slotNo = slotNo;
    }
}

class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(
            String name,
            String empId,
            Employee employee,
            ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    void fullProfile() {

        String slotInfo =
                (slot == null)
                        ? "no parking assigned"
                        : slot.slotNo;

        double pay;

        if (employee
                instanceof ManagerEmployee)
            pay = ((ManagerEmployee)
                    employee)
                    .effectiveSalary();
        else
            pay =
                    employee.getSalary();

        System.out.println(
                name
                        + " | Pay: Rs "
                        + pay
                        + " | Slot: "
                        + slotInfo);
    }
}

public class HRParkingManagementSystem {

    public static void main(
            String[] args) {

        ParkingSlot s1 =
                new ParkingSlot("A1");

        ParkingSlot s2 =
                new ParkingSlot("A2");

        CompanyEmployeeRecord e1 =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E101",
                        new ManagerEmployee(
                                70000,
                                8000),
                        s1);

        CompanyEmployeeRecord e2 =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E102",
                        new Employee(
                                40000),
                        s2);

        CompanyEmployeeRecord e3 =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E103",
                        new Employee(
                                10000),
                        null);

        e1.fullProfile();
        e2.fullProfile();
        e3.fullProfile();

        System.out.println(
                "Total records: "
                        + CompanyEmployeeRecord
                        .totalRecords);
    }
}