package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Solution300Test {
    
    @Test 
    public void test1() {
        Solution300 solution = new Solution300();
        int[] nums = {0,1,0,3,2,3};
        assertEquals(4, solution.lengthOfLIS(nums));
    }

    @Test 
    public void test2() {
        Solution300 solution = new Solution300();
        int[] nums = {10,9,2,5,3,7,101,18};
        assertEquals(4, solution.lengthOfLIS(nums));
    }
}
