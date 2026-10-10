public class ProductPriceFinder {

    static int lowerBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    static int upperBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] > target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};

        int low = 150;
        int high = 300;

        int start = lowerBound(prices, low);
        int end = upperBound(prices, high);

        System.out.println("Lower bound index " + start);
        System.out.println("Upper bound index " + end);
        System.out.println("Count " + (end - start));
    }
}