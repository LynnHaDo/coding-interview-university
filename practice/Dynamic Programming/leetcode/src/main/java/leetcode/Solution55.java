package leetcode;

public class Solution55 {
    /**
     * You are given an integer array nums. You are initially positioned at the
     * array's first index, and each element in the array represents your maximum
     * jump length at that position.
     * 
     * @param nums
     * @return Return true if you can reach the last index, or false otherwise.
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }

        int destinationIndex = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (destinationIndex <= i + nums[i]) {
                destinationIndex = i; 
            }
        }

        return destinationIndex == 0;
    }
}
