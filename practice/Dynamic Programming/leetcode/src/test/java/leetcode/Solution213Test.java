package leetcode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution213Test {
    Solution213 solution;

    @Before 
    public void setup() {
        solution = new Solution213();
    }

    @Test 
    public void test1() {
        // nums = [2,3,2]
        // output = 3
        int[] nums = {2, 3, 2};
        // 0 3 3 
        // 0 2 2
        // maxAmt = {0, 2, 3, 3}
        assertEquals(3, solution.rob(nums));
    }

    @Test 
    public void test2() {
        // nums = [1,2,3,1]
        // output = 4
        int[] nums = {1, 2, 3, 1};
        assertEquals(4, solution.rob(nums));
    }

    @Test 
    public void test3() {
        // nums = [1,2,3]
        // output = 3
        int[] nums = {1, 2, 3};
        assertEquals(3, solution.rob(nums));
    }
}
