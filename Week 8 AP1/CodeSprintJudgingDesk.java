import java.util.*;

interface ScoringRule {
    double calculate(int idea, int execution, int presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculate(int idea, int execution, int presentation) {
        return idea * 0.5 + execution * 0.3 + presentation * 0.2;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(int idea, int execution, int presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;

    Project(String name) {
        this.name = name;
    }
}

class Team {
    String name;
    List<Student> members;
    String track;
    ScoringRule scoringRule;
    Project project;
    boolean scored = false;

    Team(String name, List<Student> members, String track,
         ScoringRule scoringRule) {
        this.name = name;
        this.members = members;
        this.track = track;
        this.scoringRule = scoringRule;
    }

    public boolean validTeam() {
        return members.size() >= 2 && members.size() <= 4;
    }

    public void submitProject(Project project) {
        if (this.project == null) {
            this.project = project;
            System.out.println("Project '" + project.name +
                    "' submitted by " + name + ".");
        }
    }
}

class Judge {
    String name;

    Judge(String name) {
        this.name = name;
    }
}

class Score {
    double finalScore;

    Score(double finalScore) {
        this.finalScore = finalScore;
    }
}

class Hackathon {
    String name;
    String state = "Open";
    List<Team> teams = new ArrayList<>();
    Set<Student> registeredStudents = new HashSet<>();
    Map<Team, Score> scores = new HashMap<>();

    Hackathon(String name) {
        this.name = name;
    }

    public boolean registerTeam(Team team) {

        if (!team.validTeam()) {
            System.out.println(
                    "Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Student student : team.members) {
            if (registeredStudents.contains(student)) {
                System.out.println(
                        "Registration failed: Student already belongs to a team.");
                return false;
            }
        }

        teams.add(team);
        registeredStudents.addAll(team.members);

        System.out.println("Team " + team.name +
                " registered (" + team.members.size() +
                " members, " + team.track + " track).");

        return true;
    }

    public void recordScore(Team team, int idea,
                            int execution, int presentation) {

        if (state.equals("Published")) {
            System.out.println(
                    "Rescore rejected: Results have already been published.");
            return;
        }

        double result = team.scoringRule.calculate(
                idea, execution, presentation);

        scores.put(team, new Score(result));

        System.out.println(
                "Score recorded for '" +
                team.project.name + "'.");

        System.out.printf("Final score: %.2f%n", result);
    }

    public void publishResults() {
        state = "Published";
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {

    public static void main(String[] args) {

        Hackathon hackathon = new Hackathon("Code Sprint");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters = new Team(
                "ByteBusters",
                Arrays.asList(asha, ravi, neha),
                "Innovation",
                new InnovationScoring());

        Team soloCoder = new Team(
                "SoloCoder",
                Arrays.asList(kiran),
                "Open",
                new OpenScoring());

        hackathon.registerTeam(byteBusters);
        hackathon.registerTeam(soloCoder);

        byteBusters.submitProject(
                new Project("SmartAttend"));

        Judge judge = new Judge("Judge");

        hackathon.recordScore(
                byteBusters, 8, 7, 9);

        hackathon.publishResults();

        hackathon.recordScore(
                byteBusters, 10, 7, 9);
    }
}