import java.util.*;

interface Question {
    boolean evaluate(String answer);
}

class MultipleChoiceQuestion implements Question {
    private int questionNumber;
    private String questionText;
    private String correctAnswer;

    MultipleChoiceQuestion(int questionNumber, String questionText, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    Examination(String title) {
        this.title = title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public void answerQuestion(int questionNumber, String answer) {
        if (!submitted) {
            answers.put(questionNumber, answer);
            System.out.println("Question " + questionNumber +
                    " answered with '" + answer + "'.");
        }
    }

    public void submit() {
        if (submitted) {
            return;
        }

        submitted = true;

        System.out.println("Examination '" + examination.getTitle()
                + "' submitted successfully.");

        evaluate();
    }

    private void evaluate() {
        int correct = 0;

        for (int i = 0; i < examination.getQuestions().size(); i++) {
            String answer = answers.get(i + 1);

            if (answer != null &&
                    examination.getQuestions().get(i).evaluate(answer)) {
                correct++;
            }
        }

        System.out.println("Result for '" + examination.getTitle()
                + "' attempt: " + correct + "/"
                + examination.getQuestions().size() + " correct.");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {

        Student student = new Student("Student");

        Examination exam = new Examination("Math Quiz");

        exam.addQuestion(new MultipleChoiceQuestion(
                1, "Question 1", "A"));

        exam.addQuestion(new MultipleChoiceQuestion(
                2, "Question 2", "B"));

        System.out.println("Examination '" + exam.getTitle()
                + "' started by " + student.getName() + ".");

        Attempt attempt = new Attempt(student, exam);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();
    }
}