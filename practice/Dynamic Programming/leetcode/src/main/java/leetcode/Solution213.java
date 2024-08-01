package src;

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

        int[] maxAmt = new int[nums.length + 1];
        maxAmt[0] = 0;
        maxAmt[1] = nums[0];

        boolean isFirstHouseRobbed = false;

        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {

            }
            maxAmt[i+1] = Math.max(maxAmt[i], // not robbing the current house
                                    maxAmt[i-1] + nums[i]); // rob the current house
        }

        return maxAmt[nums.length];
    }
}
