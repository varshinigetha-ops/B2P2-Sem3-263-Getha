public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] students = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};

        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };

        for (int i = 0; i < students.length; i++) {
            int total = 0;

            for (int j = 0; j < subjects.length; j++) {
                if (marks[i][j] != -1)
                    total += marks[i][j];
            }

            System.out.println("Totals " + students[i] + " " + total);
        }

        for (int j = 0; j < subjects.length; j++) {
            int highest = -1;
            int topper = -1;

            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] > highest) {
                    highest = marks[i][j];
                    topper = i;
                }
            }

            if (topper != -1) {
                System.out.println(
                    "Topper " + subjects[j] + " " +
                    students[topper] + " " + highest
                );
            }
        }
    }
}

