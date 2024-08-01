package leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Solution371Test {
    Solution371 solution;

    @Before 
    public void setup(){
        solution = new Solution371();
    }

    @Test 
    public void test1() {
        int a = 2;
        int b = 3;
        assertEquals(solution.getSum(a, b), 5);
    }
}
