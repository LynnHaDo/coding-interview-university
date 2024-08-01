package leetcode;

import java.util.Arrays;

public class Solution238 {
    // O(n^2)
    public int[] bruteForce(int[] nums) {
        int[] answer = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            int pointer = 0;
            for (; pointer < nums.length; pointer ++) {
                if (pointer != i){
                    product *= nums[pointer];
                } 
            }
            answer[i] = product;
        }

        return answer;
    }

    // O(3n) (time), O(2n) (space)
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];

        int[] pre = new int[nums.length];
        int[] suf = new int[nums.length];

        pre[0] = 1;
        suf[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++) {
            pre[i] = nums[i - 1] * pre[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            suf[i] = nums[i + 1] * suf[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            answer[i] = pre[i] * suf[i];
        }
        
        return answer;
    }

    public static void main(String[] args) {
        Solution238 solution = new Solution238();
        int[] arr = {-1,1,0,-3,3};

        System.out.println(Arrays.toString(solution.bruteForce(arr)));
        System.out.println(Arrays.toString(solution.productExceptSelf(arr)));
    }
}
