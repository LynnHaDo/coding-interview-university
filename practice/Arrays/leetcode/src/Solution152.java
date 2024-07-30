package src;
public class Solution152 {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int result = nums[0];
        int max = nums[0];
        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0){
                max = 1;
                min = 1;
                result = Math.max(0, result);
            } else {
                int prevMax = max;
                max = Math.max(min * nums[i], max * nums[i]) > nums[i] ? Math.max(min * nums[i], max * nums[i]) : nums[i];
                min = Math.min(min * nums[i], prevMax * nums[i]) < nums[i] ? Math.min(min * nums[i], prevMax * nums[i]) : nums[i];
                result = Math.max(max, result);
            }
        }

        return result;
    }

    public static void main(String[] args){
        Solution152 solution = new Solution152();
        int[] arr = {-2, 0, -1};
        System.out.println(solution.maxProduct(arr));
    }
}
