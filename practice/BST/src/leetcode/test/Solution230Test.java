package leetcode.test;

import leetcode.src.Solution230;
import leetcode.src.TreeNode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution230Test {
    Solution230 solution;

    @Before 
    public void setup() {
        solution = new Solution230();
    }

    @Test 
    public void test1() {
        // root = [3,1,4,null,2], k = 1
        // return 1
    }

    @Test 
    public void test2() {
        // root = [5,3,6,2,4,null,null,1], k = 3
        // return 3
    }
}
