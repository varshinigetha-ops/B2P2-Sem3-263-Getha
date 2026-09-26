import java.time.LocalDate;

abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    public abstract boolean isLeaveAllowed(int days);

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {

    FullTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {

    PartTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 15;
    }
}

class ContractEmployee extends Employee {

    ContractEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days <= 10;
    }
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    LeaveRequest(Employee employee,
                 LocalDate startDate,
                 LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
    }

    public void approve() {

        if (status.equals("Pending")) {
            status = "Approved";

            System.out.println("Leave request for " +
                    employee.getName() +
                    " approved. Status: Approved.");
        }
    }

    public void reject() {

        if (status.equals("Pending")) {
            status = "Rejected";

            System.out.println("Leave request for " +
                    employee.getName() +
                    " rejected. Status: Rejected.");
        }
    }

    public void setPending() {

        if (!status.equals("Pending")) {
            System.out.println(
                    "Cannot change status: " +
                    status +
                    " request cannot revert to Pending.");
        }
    }
}

class LeaveManager {

    public LeaveRequest submitLeave(Employee employee,
                                     LocalDate start,
                                     LocalDate end) {

        long days = java.time.temporal.ChronoUnit.DAYS.between(
                start, end) + 1;

        if (!employee.isLeaveAllowed((int) days)) {
            System.out.println("Leave request rejected due to leave policy.");
            return null;
        }

        LeaveRequest request =
                new LeaveRequest(employee, start, end);

        System.out.println(
                "Leave request submitted by " +
                employee.getName() +
                " for " + start + " to " + end +
                ". Status: Pending.");

        return request;
    }
}

public class EmployeeLeaveManagement {
    public static void main(String[] args) {

        LeaveManager manager = new LeaveManager();

        Employee john = new FullTimeEmployee("John Doe");

        LeaveRequest request1 = manager.submitLeave(
                john,
                LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12));

        request1.approve();

        Employee jane = new PartTimeEmployee("Jane Smith");

        LeaveRequest request2 = manager.submitLeave(
                jane,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5));

        request1.setPending();
    }
}