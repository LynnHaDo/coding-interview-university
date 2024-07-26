package leetcode.test;

import org.junit.Test;

import leetcode.src.Solution191;

import static org.junit.Assert.assertEquals;

public class Solution191Test {
    @Test 
    public void test() {
        Solution191 solution = new Solution191();

        assertEquals(3, solution.hammingWeight(11));
    }

    @Test 
    public void test1() {
        Solution191 solution = new Solution191();

        assertEquals(1, solution.hammingWeight(128));
    }

    @Test 
    public void test2() {
        Solution191 solution = new Solution191();

        assertEquals(30, solution.hammingWeight(2147483645));
    }
}
