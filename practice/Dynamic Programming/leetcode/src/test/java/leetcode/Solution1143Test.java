package test;

import org.junit.Test;

import src.Solution1143;

import static org.junit.Assert.assertEquals;

public class Solution1143Test {
    
    @Test 
    public void test1() {
        Solution1143 solution = new Solution1143();
        String text1 = "abcde";
        String text2 = "ace";

        assertEquals(solution.longestCommonSubsequence(text1, text2), 3);
    }

    @Test 
    public void test2() {
        Solution1143 solution = new Solution1143();
        String text1 = "abcde";
        String text2 = "";

        assertEquals(solution.longestCommonSubsequence(text1, text2), 0);
    }

    @Test 
    public void test3() {
        Solution1143 solution = new Solution1143();
        String text1 = "abs";
        String text2 = "abs";

        assertEquals(solution.longestCommonSubsequence(text1, text2), 3);
    }
}
