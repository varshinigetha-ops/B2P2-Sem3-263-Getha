public class LongestBudgetFriendlyStreak {

    static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        long sum = 0;

        int maxLength = 0;
        int startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (sum > budget && left <= right) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > maxLength) {
                maxLength = length;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};

        int[] result = longestStreak(costs, 8);

        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}
