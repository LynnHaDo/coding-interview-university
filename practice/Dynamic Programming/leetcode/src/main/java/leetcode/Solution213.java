package leetcode;

import java.util.Arrays;

public class Solution213 {
    /**
     * You are a professional robber planning to rob houses along a street. Each
     * house has a certain amount of money stashed. All houses at this place are
     * arranged in a circle. That means the first house is the neighbor of the last
     * one. Meanwhile, adjacent houses have a security system connected, and it will
     * automatically contact the police if two adjacent houses were broken into on
     * the same night.
     * 
     * Given an integer array nums representing the amount of money of each house,
     * return the maximum amount of money you can rob tonight without alerting the
     * police.
     * 
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // Can only rob the only house
        if (nums.length == 1) {
            return nums[0];
        }

        // Can only rob either of the 2
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // Compute 2 arrays:
            // Max amount if excluding the first house
            // Max amount if excluding the last house
            // Max is the greater of the 2

        // Max amount if excluding the first house
        int[] maxAmt1 = new int[nums.length];
        maxAmt1[0] = 0;
        maxAmt1[1] = nums[1]; // skip over the first house

        for (int i = 2; i < nums.length; i++) {
            maxAmt1[i] = Math.max(maxAmt1[i-1], // not robbing the current house
                                    maxAmt1[i-2] + nums[i]); // rob the current house
        }

        int[] maxAmt2 = new int[nums.length];
        maxAmt2[0] = 0;
        maxAmt2[1] = nums[0]; 

        for (int i = 1; i < nums.length - 1; i++) {
            maxAmt2[i+1] = Math.max(maxAmt2[i], // not robbing the current house
                                    maxAmt2[i-1] + nums[i]); // rob the current house
        }

        return Math.max(maxAmt1[nums.length - 1], maxAmt2[nums.length - 1]);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution213 solution = new Solution213();
        solution.rob(nums);
    }
}
