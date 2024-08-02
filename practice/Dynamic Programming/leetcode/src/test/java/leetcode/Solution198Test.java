package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class Solution198Test {
    Solution198 solution;

    @Before
    public void setup() {
       solution = new Solution198();
    }
    
    @Test 
    public void test1() {
        int[] nums = {1, 2, 3, 1};
        assertEquals(solution.rob(nums), 4);
    }

    @Test 
    public void test2() {
        int[] nums = {2,7,9,3,1};
        assertEquals(solution.rob(nums), 12);
    }
}
