import java.util.*;

public class NetBalancePeriodCounter {

    static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> map = new HashMap<>();

        map.put(0L, 1);

        long sum = 0;
        long count = 0;

        for (int value : transactions) {
            sum += value;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};

        System.out.println(countPeriods(transactions, 7));
    }
}
