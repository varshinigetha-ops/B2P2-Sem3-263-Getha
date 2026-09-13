import java.util.Scanner;

public class Word_Reversal_Encoder {

    static String reverseEachWord(String sentence) {

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {

            StringBuilder reverse = new StringBuilder();

            for (int i = word.length() - 1; i >= 0; i--) {
                reverse.append(word.charAt(i));
            }

            result.append(reverse).append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        System.out.println(reverseEachWord(sentence));
    }
}