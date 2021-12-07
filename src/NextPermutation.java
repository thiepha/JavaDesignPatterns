/*
31. Next Permutation
Implement next permutation, which rearranges numbers into
the lexicographically next greater permutation of numbers.

If such arrangement is not possible,
it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.
Here are some examples. Inputs are in the left-hand column and
its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

import java.util.Arrays;

public class NextPermutation {
    static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 0) return;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j > i; j--) {
                if (nums[i] < nums[j]) {
                    swap(nums, i, j);
                    // reverse the remain
                    int mid = (len - 1 + i + 1) / 2; // from i + 1 to len - 1
                    int l = len - 1;
                    for (int k = i + 1; k <= mid; k++) {
                        swap(nums, k, l);
                        l--;
                    }
//                    for (int k = i + 1; k < len; k++) {
//                        int n = nums[k], pos = k;
//                        for (int l = k + 1; l < len; l++) {
//                            if (nums[l] < n) {
//                                n = nums[l];
//                                pos = l;
//                            }
//                        }
//                        if (pos != k) swap(nums, pos, k);
//                    }
                    return;
                }
            }
        }
        // sort
        Arrays.sort(nums);
    }

    public static void nextPer2(int[] nums) {
        int len = nums.length;
        if (len == 0) return;
        int i;
        for (i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) break;
        }
        if (i >= 0) {
            for (int j = len - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
        reverse(nums, i + 1);
    }

    private static void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] ns1 = {1, 3, 2};
        System.out.println(Arrays.toString(ns1));
//        nextPermutation(ns1);
        nextPer2(ns1);
        System.out.println(Arrays.toString(ns1));
        int[] ns2 = {3, 2, 1};
//        nextPermutation(ns2);
        nextPer2(ns2);
        System.out.println(Arrays.toString(ns2));
        int[] ns3 = {1, 1, 5};
//        nextPermutation(ns3);
        nextPer2(ns3);
        System.out.println(Arrays.toString(ns3));
        int[] ns4 = {2, 4, 3, 1, 0};
//        nextPermutation(ns4); // [3, 0, 1, 2, 4]
        nextPer2(ns4);
        System.out.println(Arrays.toString(ns4));

        int[] ns5 = {4, 2, 0, 2, 3, 2, 0};
//        nextPermutation(ns5); //{4, 2, 0, 3, 0, 2, 2}
        nextPer2(ns5);
        System.out.println(Arrays.toString(ns5));
    }
}
