import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        return check(text, 0, text.length() - 1);
    }

    public static boolean check(String text, int left, int right) {
        if (left >= right)
            return true;

        if (text.charAt(left) != text.charAt(right))
            return false;

        return check(text, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            reversed[i] = original[text.length() - 1 - i];
        }

        return text.equals(new String(reversed));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a text: ");
        String text = sc.nextLine();

        System.out.print("Iterative: ");
        System.out.print(isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome");

        System.out.print(" | Recursive: ");
        System.out.print(isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome");

        System.out.print(" | Array Reversal: ");
        System.out.print(isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome");

        sc.close();
    }
}