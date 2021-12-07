/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 (5, 5, 1)
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

coins = [2, 3, 7], amount = 8
Output: 3 (3, 3, 2)

Note:
You may assume that you have an infinite number of each kind of coin.
*/

import java.util.Arrays;

public class CoinChange {
    private static int changeHelper(int[] coins, int amount, int pos) {
        System.out.println("amount: " + amount + "; pos: " + pos);
        if (amount == 0) return 0;
        if (pos < 0) return -1;
        int curcoin = coins[pos];
        if (amount == curcoin) {
            System.out.println("found: " + curcoin);
            return 1;
        }
        else if (amount > curcoin) {
            int ret = changeHelper(coins, amount - curcoin, pos);
            while (ret == -1 && pos > 0) {
                pos--;
                ret = changeHelper(coins, amount - coins[pos], pos);
            }
            if (ret == -1) return ret;
            System.out.println("found one: " + coins[pos] + "; remain: " + (amount - coins[pos]));
            return ret + 1;
        } else {
            pos--;
            while (pos >= 0) {
                if (amount >= coins[pos]) {
                    break;
                }
                pos--;
            }
            if (pos < 0) return -1;
            if (amount == coins[pos]) {
                System.out.println("found: " + curcoin);
                return 1;
            }
            int ret = changeHelper(coins, amount - coins[pos], pos);
            while (ret == -1 && pos > 0) {
                pos--;
                ret = changeHelper(coins, amount - coins[pos], pos);
            }
            if (ret == -1) return ret;
            System.out.println("found one: " + coins[pos] + "; remain: " + (amount - coins[pos]));
            return ret + 1;
        }
    }

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return changeHelper(coins, amount, coins.length - 1);
    }

    private static int changeAllHelper(int[] coins, int amount, int pos) {
        if (amount == 0) return 0;
        if (pos < coins.length && pos >= 0) {
            int maxVal = amount / coins[pos];
            int minCount = Integer.MAX_VALUE;
            for (int n = 0; n <= maxVal; n++) {
                if (amount >= n * coins[pos]) {
                    int ret = changeAllHelper(coins, amount - n * coins[pos], pos + 1);
                    if (ret != -1) {
                        minCount = Math.min(minCount, ret + n);
                    }
                }
            }
            return (minCount == Integer.MAX_VALUE) ? -1 : minCount;
        }
        return -1;
    }

    public static int coinChangeAll(int[] coins, int amount) {
        return changeAllHelper(coins, amount, 0);
    }

    private static int topDownDP(int[] coins, int amount, int[] nums) {
        if (amount == 0) return 0;
        if (nums[amount] != 0) return nums[amount];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                int ret = topDownDP(coins, amount - coins[i], nums);
                if (ret != Integer.MAX_VALUE && ret != -1) {
                    min = Math.min(min, ret + 1);
                }
            }
        }
        nums[amount] = min < Integer.MAX_VALUE ? min : -1;
        return nums[amount];
    }

    public static int coinChangeTopDownDP(int[] coins, int amount) {
        int[] nums = new int[amount + 1];
//        Arrays.fill(nums, Integer.MAX_VALUE);
        return topDownDP(coins, amount, nums);
    }

    public static int coinChange1(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange1(coins, amount, new int[amount]);
    }

    private static int coinChange1(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange1(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public static void main(String[] args) {
        int[] cs1 = {1, 2, 5};
//        System.out.println(coinChange(cs1, 11)); //3
        System.out.println(coinChangeAll(cs1, 11)); //3
        System.out.println(coinChangeTopDownDP(cs1, 11)); //3
        int[] cs2 = {2, 3, 7};
//        System.out.println(coinChange(cs2, 4)); // 2
        System.out.println(coinChangeAll(cs2, 4)); // 2
        System.out.println(coinChangeTopDownDP(cs2, 1)); // 2
        int[] cs3 = {186, 419, 83, 408}; // [83, 186, 408, 419]
//        System.out.println(coinChange(cs3, 6249)); //20
//        System.out.println(coinChangeAll(cs3, 6249)); //20
        System.out.println(coinChangeTopDownDP(cs3, 6249)); //20
//        System.out.println(coinChange1(cs3, 6249)); //20
    }
}
