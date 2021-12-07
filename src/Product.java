/*
238	Product of Array Except Self
Given an array nums of n integers where n > 1,
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
*/

public class Product {
    public static int[] product(int[] input) {
        int[] output = new int[input.length];
        int[] left = new int[input.length];
        output[0] = left[0] = input[0];
        for (int i = 1; i < input.length; i++) {
            output[i] = input[i];
            left[i] = input[i] * left[i - 1];
        }

        for (int i = input.length - 2; i >= 0; i--) {
            output[i] = output[i + 1] * output[i];
        }
        output[0] = output[1];
        output[input.length - 1] = left[input.length - 2];
        for (int i = 1; i < input.length - 1; i++) {
            output[i] = left[i - 1] * output[i + 1];
        }

        return output;
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};
        System.out.println(java.util.Arrays.toString(product(input)));
    }
}
