package src;

public class Solution198 {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] maxAmt = new int[nums.length + 1];

        maxAmt[0] = 0;
        maxAmt[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxAmt[i+1] = Math.max(maxAmt[i], maxAmt[i-1] + nums[i]);
        }

        return maxAmt[nums.length];
    }
}
