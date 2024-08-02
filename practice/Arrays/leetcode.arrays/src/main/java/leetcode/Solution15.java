package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution15 {
    List<List<Integer>> threeSumList;
    Integer prevA;
    Integer prevB;

    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j],
     * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
     * nums[k] == 0.
     * 
     * Notice that the solution set must not contain duplicate triplets.
     * 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        threeSumList = new ArrayList<List<Integer>>();
        Arrays.sort(nums); // sort array
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue; // skip over duplicate 1st element
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (nums[left] + nums[right] + nums[i] == 0) {
                    if (prevA != null && prevA == nums[left] && prevB != null && nums[right] == prevB) {
                        left++;
                        right--;
                        continue;
                    }
                    
                    prevA = nums[left];
                    prevB = nums[right];
                    List<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(nums[i]);
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);
                    threeSumList.add(triplet);
                }
                else if (nums[left] + nums[right] + nums[i] < 0) {
                    left++;
                }
                else {
                    right--;
                }
                
            }
        }

        return threeSumList;
    }

    public static void main(String[] args) {
        Solution15 solution15 = new Solution15();
        int[] nums = { -2,0,1,1,2 };
        // -4 -1 -1 0 1 2
        System.out.println(solution15.threeSum(nums));
    }
}
