package leetcode;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class Solution55Test {
    Solution55 solution;

    @Before 
    public void setup() {
        solution = new Solution55();
    }

    @Test 
    public void test1() {
        int[] nums = {2,3,1,1,4};
        assertTrue(solution.canJump(nums));
    }

    @Test 
    public void test2() {
        int[] nums = {3,2,1,0,4};
        assertTrue(solution.canJump(nums));
    }

}
