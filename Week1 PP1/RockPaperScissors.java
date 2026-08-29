import java.util.*;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {

        if (playerMove.equalsIgnoreCase(computerMove))
            return "Draw";

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        int rounds = 5;
        int wins = 0, losses = 0, draws = 0;

        for (int i = 1; i <= rounds; i++) {

            System.out.print("Enter Player Move (Rock/Paper/Scissors): ");
            String playerMove = sc.next();

            String computerMove = moves[rand.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            System.out.println("\nRound " + i + " — Player: " +
                    playerMove + ", Computer: " + computerMove);
            System.out.println(result);
            System.out.println();

            if (result.equals("Player Wins"))
                wins++;
            else if (result.equals("Computer Wins"))
                losses++;
            else
                draws++;
        }

        double winPercentage = (wins * 100.0) / rounds;

        System.out.println("Final Summary (after 5 rounds)");
        System.out.println("Wins: " + wins +
                " | Losses: " + losses +
                " | Draws: " + draws +
                " | Win % = " + winPercentage + "%");

        sc.close();
    }
}