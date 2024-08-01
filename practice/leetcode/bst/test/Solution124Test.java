package leetcode.test;

import leetcode.src.Solution124;
import leetcode.src.TreeNode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution124Test {
    Solution124 solution;
    TreeNode root1;
    TreeNode root2;

    @Before 
    public void setup() {
        this.solution = new Solution124();

        // [1, 2, 3]: 6
        root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        // [-10,9,20,null,null,15,7]: 42 (20 + 15 + 7)
        root2 = new TreeNode(-10, new TreeNode(9), new TreeNode(20));
        root2.getRight().setLeft(new TreeNode(15));
        root2.getRight().setRight(new TreeNode(7));
    }

    @Test 
    public void test1() {
        assertEquals(solution.maxPathSum(root1), 6);
    }

    @Test 
    public void test2() {
        assertEquals(solution.maxPathSum(root2), 42);
    }
}
