import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
}

class RegularPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 24;
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 28;
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 20;
    }
}

class Student {
    String name;
    int currentCredits;
    CreditPolicy policy;

    Student(String name, int currentCredits, CreditPolicy policy) {
        this.name = name;
        this.currentCredits = currentCredits;
        this.policy = policy;
    }

    public boolean canAddCredits(int credits) {
        return currentCredits + credits <=
                policy.getCreditLimit();
    }

    public void addCredits(int credits) {
        currentCredits += credits;
    }

    public void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {
    String name;
    int credits;
    int capacity;

    List<Student> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    public boolean isEnrolled(Student student) {
        return enrolled.contains(student);
    }

    public boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    public boolean isFull() {
        return enrolled.size() >= capacity;
    }

    public void enroll(Student student) {
        enrolled.add(student);
        student.addCredits(credits);
    }

    public void remove(Student student) {
        enrolled.remove(student);
        student.removeCredits(credits);
    }
}

class EnrollmentService {

    public void enroll(Student student, Elective elective) {

        if (elective.isEnrolled(student) ||
                elective.isWaiting(student)) {
            System.out.println(
                    "Enrollment failed: Student already enrolled or waitlisted.");
            return;
        }

        if (!student.canAddCredits(elective.credits)) {
            System.out.println(
                    "Enrollment failed: " + student.name +
                    " would exceed the credit limit (" +
                    (student.currentCredits + elective.credits) +
                    "/" + student.policy.getCreditLimit() + ").");
            return;
        }

        if (elective.isFull()) {
            elective.waitlist.add(student);

            System.out.println(
                    elective.name + " is full.");

            System.out.println(
                    student.name +
                    " added to waitlist (position " +
                    elective.waitlist.size() + ").");

            return;
        }

        elective.enroll(student);

        System.out.println(
                student.name +
                " enrolled in " +
                elective.name +
                " (credits: " +
                student.currentCredits +
                "/" + student.policy.getCreditLimit() + ").");
    }

    public void drop(Student student, Elective elective) {

        if (!elective.isEnrolled(student))
            return;

        elective.remove(student);

        System.out.println(
                student.name +
                " dropped " + elective.name +
                " (credits: " +
                student.currentCredits +
                "/" + student.policy.getCreditLimit() + ").");

        promote(elective);
    }

    private void promote(Elective elective) {

        if (elective.isFull())
            return;

        Iterator<Student> iterator =
                elective.waitlist.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            if (student.canAddCredits(elective.credits)) {
                iterator.remove();

                elective.enroll(student);

                System.out.println(
                        student.name +
                        " promoted from waitlist and enrolled in " +
                        elective.name +
                        " (credits: " +
                        student.currentCredits +
                        "/" +
                        student.policy.getCreditLimit() +
                        ").");

                break;
            }
        }
    }
}

public class ElectiveSeatRush {

    public static void main(String[] args) {

        Elective elective =
                new Elective("Cloud Computing", 4, 2);

        Student asha =
                new Student("Asha", 20, new RegularPolicy());

        Student ravi =
                new Student("Ravi", 22, new HonorsPolicy());

        Student neha =
                new Student("Neha", 12, new ExchangePolicy());

        Student kiran =
                new Student("Kiran", 22, new RegularPolicy());

        EnrollmentService service =
                new EnrollmentService();

        service.enroll(asha, elective);
        service.enroll(ravi, elective);
        service.enroll(neha, elective);
        service.enroll(kiran, elective);

        service.drop(asha, elective);
    }
}