class Employee {
    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {

    private double teamBonus;

    ManagerEmployee(String id, String name,
                    double salary, double teamBonus) {
        super(id, name, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(String id, String name,
                   double salary, double stipendCap) {
        super(id, name, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeInheritanceSystem {
    public static void main(String[] args) {

        Employee plain =
                new Employee("E101", "Ravi", 40000);

        ManagerEmployee manager =
                new ManagerEmployee(
                        "E102", "Meera",
                        70000, 8000);

        InternEmployee intern =
                new InternEmployee(
                        "E103", "Divya",
                        12000, 10000);

        System.out.println(
                "Plain employee pay: Rs "
                        + plain.getSalary());

        System.out.println(
                "Manager effective pay: Rs "
                        + manager.effectiveSalary());

        System.out.println(
                "Intern effective pay: Rs "
                        + intern.effectiveSalary());
    }
}