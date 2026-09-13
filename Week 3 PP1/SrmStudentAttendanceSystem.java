public class SrmStudentAttendanceSystem {

    String name;
    String regNo;
    int attendance;

    SrmStudentAttendanceSystem(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudentAttendanceSystem[] students) {
        int sum = 0;

        for (SrmStudentAttendanceSystem s : students) {
            sum += s.attendance;
        }

        return (double) sum / students.length;
    }

    public static void main(String[] args) {

        SrmStudentAttendanceSystem[] students = {
                new SrmStudentAttendanceSystem("Ravi", "RA101", 82),
                new SrmStudentAttendanceSystem("Anitha", "RA102", 68),
                new SrmStudentAttendanceSystem("Karthik", "RA103", 91),
                new SrmStudentAttendanceSystem("Meera", "RA104", 74),
                new SrmStudentAttendanceSystem("Suresh", "RA105", 60)
        };

        for (SrmStudentAttendanceSystem s : students) {
            System.out.println(s.name + " - " + s.attendance + "% - "
                    + (s.isEligible() ? "Eligible" : "Detained"));
        }

        System.out.println("Class Average: "
                + classAverage(students) + "%");
    }
}