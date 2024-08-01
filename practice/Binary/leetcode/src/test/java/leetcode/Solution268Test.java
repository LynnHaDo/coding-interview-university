package leetcode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution268Test {
    Solution268 solution;
    
    @Before 
    public void setup() {
        solution = new Solution268();
    }

    @Test 
    public void test1() {
        // nums = [3,0,1]
        // return 2 (n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 
        // 2 is the missing number in the range since it does not appear in nums.)

        int[] nums = {3,0,1};
        assertEquals(solution.missingNumber(nums), 2);
    }

    @Test 
    public void test2() {
        // nums = [0,1]
        // return 2 (n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 
        // 2 is the missing number in the range since it does not appear in nums.)
        int[] nums = {0,1};
        assertEquals(solution.missingNumber(nums), 2);
    }

    @Test 
    public void test3() {
        // nums = [9,6,4,2,3,5,7,0,1]
        // return 8 
        int[] nums = {9,6,4,2,3,5,7,0,1};
        assertEquals(solution.missingNumber(nums), 8);
    }
}
