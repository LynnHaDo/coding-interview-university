package leetcode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution5Test {
    Solution5 solution;

    @Before 
    public void setup() {
        solution = new Solution5();
    }

    @Test 
    public void test1() {
        // input: s = "babad"
        // output: bab

        assertEquals("bab", solution.longestPalindrome("babad"));
    }

    @Test 
    public void test2() {
        // input: s = "cbbd"
        // output: "bb"

        assertEquals("bb", solution.longestPalindrome("cbbd"));
    }

}
