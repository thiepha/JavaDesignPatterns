/*
33. Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
*/

public class SearchRotatedArray {
    public static int search1(int[] nums, int target) {
        int len = nums.length;
        if (len <= 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;
        int l = 0, r = len - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            if (nums[l] < nums[r]) {
                if (nums[m] < target) l = m + 1;
                else r = m - 1;
            } else {
                if (nums[l] <= nums[m]) {
                    if (nums[m] > target && target >= nums[l]) r = m - 1;
                    else l = m + 1;
                } else {
                    if (nums[m] < target && nums[l] > target) l = m + 1;
                    else r = m - 1;
                }
            }
        }
        return -1;
    }
    static int binarySearch(int[] nums, int target, int l, int r) {
        if (l > r) return -1;
        int m = l + (r - l) / 2;
        if (nums[m] == target) return m;
        else if (nums[m] < target) return binarySearch(nums, target, m + 1, r);
        else return binarySearch(nums, target, l, m - 1);
    }

    public static int search(int[] nums, int target) {
        int len = nums.length;
        if (len <= 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;
        if (nums[0] <= nums[len - 1]) {
            return binarySearch(nums, target, 0, len - 1);
        } else { // rotated
            // find the rotated pos
            int pos = 0;
            int l = 0, r = len - 1;
            while (l < r && l < len && r >= 0) {
                int m = l + (r - l) / 2;
                if (nums[m] > nums[0]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
//            System.out.println("l: " + l + "; r: " + r);
            if (nums[l] < nums[0]) pos = l;
            else pos = l + 1;
            System.out.println("Pos: " + pos);
            if (nums[0] > target) return binarySearch(nums, target, pos, len - 1);
            else return binarySearch(nums, target, 0, pos - 1);
        }
    }

    public static void main(String[] args) {
        /*int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search1(nums, 7));
        int[] nums2 = {5, 1, 3};
        System.out.println(search1(nums2, 1));
        int[] nums3 = {8, 9, 2, 3, 4};
        System.out.println(search1(nums3, 9));
        int[] nums4 = {1, 2, 3};
        System.out.println(search1(nums4, 1));
        int[] nums5 = {4, 5, 6, 7, 8, 1, 2, 3};
        System.out.println(search1(nums5, 8));*/
        int[] nums6 = {5, 1, 2, 3, 4};
        System.out.println(search1(nums6, 1));
    }
}
