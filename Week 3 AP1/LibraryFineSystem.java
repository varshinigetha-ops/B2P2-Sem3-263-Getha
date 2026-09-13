class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5 : 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}

public class LibraryFineSystem {
    public static void main(String[] args) {

        BookIssue[] issues = {
                new BookIssue("Clean Code", "Ravi", 18),
                new BookIssue("Effective Java", "Anitha", 5),
                new BookIssue("Refactoring", "Karthik", 0),
                new BookIssue("DSA Handbook", "Meera", 21),
                new BookIssue("Design Patterns", "Suresh", 9)
        };

        for (BookIssue issue : issues) {
            System.out.println(issue.title + " - "
                    + issue.daysOverdue + " days - "
                    + (issue.isSeverelyOverdue()
                    ? "Severely overdue" : "OK"));
        }

        System.out.println("Total fine collected: Rs "
                + BookIssue.totalFineCollected(issues));
    }
}