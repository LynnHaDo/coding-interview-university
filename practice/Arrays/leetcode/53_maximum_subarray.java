class Solution53 {

    // Brute force: Compute every subarray containing each element -> Find the largest

    public int maxSubArray(int[] nums) {

        int maxSub = nums[0];
        int curSum = 0;

        for (int num : nums) {
            if (curSum < 0) {
                curSum = 0; // disregard everything up until this point
            }

            curSum += num;
            maxSub = Math.max(maxSub, curSum);
        }
        return maxSub;
    }

    public static void main(String[] args) {
        Solution53 solution = new Solution53();

        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray(arr));
    }
}
