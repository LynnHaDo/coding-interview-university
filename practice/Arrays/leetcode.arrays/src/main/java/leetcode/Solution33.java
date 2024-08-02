package leetcode;

public class Solution33 {
    private int recursiveSearch(int[] nums, int target, int startIndex, int endIndex) {
        if (endIndex == startIndex) {
            return nums[startIndex] == target? startIndex : -1;
        }

        int mid = startIndex + (endIndex - startIndex)/2;

        if (target == nums[mid]) {
            return mid;
        }

        if (nums[mid] >= nums[startIndex]) {
            if (target < nums[mid] && target >= nums[startIndex]) {
                return recursiveSearch(nums, target, startIndex, mid-1);
            }
            return recursiveSearch(nums, target, mid+1, endIndex);
        }

        else {
            if (target > nums[mid] && target <= nums[endIndex]) {
                return recursiveSearch(nums, target, mid+1, endIndex);
            }
            return recursiveSearch(nums, target, startIndex, mid-1);
        }
    }
    /**
     * There is an integer array nums sorted in ascending order (with distinct
     * values).
     * 
     * Prior to being passed to your function, nums is possibly rotated at an
     * unknown pivot index k (1 <= k < nums.length) such that the resulting array is
     * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
     * (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3
     * and become [4,5,6,7,0,1,2].
     * 
     * Given the array nums after the possible rotation and an integer target,
     * return the index of target if it is in nums, or -1 if it is not in nums.
     * 
     * You must write an algorithm with O(log n) runtime complexity.
     * 
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target? 0 : -1;
        }

        return recursiveSearch(nums, target, 0, nums.length - 1);
    }
}
