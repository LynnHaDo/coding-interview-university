package leetcode;

public class Solution268 {
    /**
     * [Easy way]
     * Given an array nums containing n distinct numbers 
     * in the range [0, n], return the only number in 
     * the range that is missing from the array.
     * 
     * @param nums
     * @return 
     */
    public int missingNumber(int[] nums) {
        int expectedSum = ((nums.length) * (nums.length + 1))/2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }
}