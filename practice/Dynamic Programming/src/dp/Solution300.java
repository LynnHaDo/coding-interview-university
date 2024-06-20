package src.dp;
import java.util.Arrays;

public class Solution300 {
    public Solution300() {}

    /**
     * Given an integer array nums, return the length of the longest strictly increasing subsequence
     * Example: nums = [10,9,2,5,3,7,101,18] => 4 (2, 3, 7, 101)
     * 
     * Constraint: 1 <= nums.length <= 2500
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int[] lis = new int[nums.length];
        lis[nums.length-1] = 1; // base case

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                } 
            }
        }

        Arrays.sort(lis);

        return lis[nums.length - 1];
    }
}