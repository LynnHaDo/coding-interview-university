package leetcode.test;

import leetcode.src.Solution98;
import leetcode.src.TreeNode;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class Solution98Test {
    Solution98 solution;
    TreeNode root1;
    TreeNode root2;

    @Before 
    public void setup() {
        solution = new Solution98();
        // 2, 1, 3 (valid tree)
        root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        // 5,1,4,null,null,3,6 (invalid tree)
        root2 = new TreeNode(5, new TreeNode(1), new TreeNode(4));
        root2.getRight().setLeft(new TreeNode(3));
        root2.getRight().setRight(new TreeNode(6));
    }

    @Test 
    public void testInvalidTree() {
        assertTrue(solution.isValidBST(root1));
    }

    @Test 
    public void testValidTree() {
        assertFalse(solution.isValidBST(root2));
    }
}
