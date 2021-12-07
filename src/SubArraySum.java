import java.util.HashMap;
import java.util.Map;

/*
560. Subarray Sum Equals K

Given an array of integers and an integer k,
you need to find the total number of continuous subarrays
whose sum equals to k.

Example 1:

Input:nums = [1,1,1], k = 2
Output: 2

nums = [1, 2, 3, 1, 4, 1], k = 6
Output: 3

Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and
the range of the integer k is [-1e7, 1e7].
*/
public class SubArraySum {
    public static int subarraySum(int[] nums, int k) {
        if (nums.length <= 0) return 0;
        int pos = 0, sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = pos; j < nums.length; j++) {
                sum  += nums[j];
                pos = j + 1;
                if (sum >= k) {
                    if (sum > k) {
                        sum -= nums[j];
                        pos = j;
                    }
                    break;
                }
            }
            if (sum == k) count++;
            sum -= nums[i];
        }
        return count;
    }

    static int subarraySum1 (int[] nums, int k) {
        // think it as dynamic programming problem
        Map<Integer, Integer> map = new HashMap<>();

        int res = 0;
        int sum = 0;

        // empty array's sum is zero. (although doesn't count towards result)
        map.put(0, 1);

        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            // map.get(q) means number of sub-arrays [0, j) that adds up to q, where j <= i
            // if sum [j, i] == k then sum [0, j) = sum - k
            // this would be the number of sub-arrays [j, i] where j >= 0 and j <= i that adds up to K
            res += map.getOrDefault(sum - k, 0);

            map.compute(sum, (key, v) -> v == null ? 1 : v + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -1, 4, 1};
//        map = {0: 1, 1: 1, 3: 1, 6: 1, 5: 1, 9: 1, 10: 1};
        int[] ret = new int[7];
        int[] expect = {0, 3, 1, 2, 2, 3, 3};
        for (int i = 0; i <= 6; i++) {
            ret[i] = subarraySum1(nums, i);
        }
        System.out.println("Expect: " + java.util.Arrays.toString(expect));
        System.out.println("Got:    " + java.util.Arrays.toString(ret));
//        System.out.println("0: " + subarraySum(nums, 0));
//        System.out.println("1: " + subarraySum(nums, 1));
//        System.out.println("2: " + subarraySum(nums, 2));
//        System.out.println("3: " + subarraySum(nums, 3));
//        System.out.println("4: " + subarraySum(nums, 4));
//        System.out.println("5: " + subarraySum(nums, 5));
//        System.out.println("6: " + subarraySum(nums, 6));
    }
}
