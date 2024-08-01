package leetcode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution33Test {
    Solution33 solution;

    @Before 
    public void setup() {
        solution = new Solution33();
    }

    @Test 
    public void test1() {
        // nums = [4,5,6,7,0,1,2], target = 0
        // output: 4

        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        assertEquals(4, solution.search(nums, target));
    }

    // 3 5 1

    @Test 
    public void test2() {
        // nums = [4,5,6,7,0,1,2], target = 3
        // output: -1

        int[] nums = {4,5,6,7,0,1,2};
        int target = 3;

        assertEquals(-1, solution.search(nums, target));
    }

    @Test 
    public void test3() {
        // nums = [1], target = 0
        // output: -1

        int[] nums = {1};
        int target = 0;

        assertEquals(-1, solution.search(nums, target));
    }

    @Test 
    public void test4() {
        // nums = [1], target = 0
        // output: -1

        int[] nums = {1,3};
        int target = 1;

        assertEquals(0, solution.search(nums, target));
    }

    @Test 
    public void test5() {
        // nums = [1], target = 0
        // output: -1

        int[] nums = {3, 5, 1};
        int target = 3;
        // 1 2 3

        assertEquals(0, solution.search(nums, target));
    }

    @Test 
    public void test6() {
        // nums = [1], target = 0
        // output: -1

        int[] nums = {1,3};
        int target = 0;

        assertEquals(-1, solution.search(nums, target));
    }
}