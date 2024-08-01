package leetcode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution338Test {
    Solution338 solution;

    @Before 
    public void setup() {
        solution = new Solution338();
    }

    @Test 
    public void test1() {
        // Input: n = 2 (0: 0; 1: 1; 2: 10)
        // Output: [0,1,1] 
        int[] expected = {0, 1, 1};
        assertArrayEquals(expected, solution.countBits(2));
    }

    @Test 
    public void test2() {
        // Input: n = 5 (0: 0; 1: 1; 2: 10; 3: 11; 4: 100; 5: 101)
        // Output: [0,1,1,2,1,2] 
        int[] expected = {0,1,1,2,1,2};
        assertArrayEquals(expected, solution.countBits(5));
    }
}
