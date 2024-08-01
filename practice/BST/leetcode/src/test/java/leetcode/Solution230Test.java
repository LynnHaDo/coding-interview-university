package leetcode.test;

import leetcode.src.Solution230;
import leetcode.src.TreeNode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution230Test {
    Solution230 solution;
    TreeNode root;

    @Before 
    public void setup() {
        solution = new Solution230();
    }

    @Test 
    public void test1() {
        root = new TreeNode(3, new TreeNode(1), new TreeNode(4));
        root.getLeft().setRight(new TreeNode(2));

        assertEquals(1, solution.kthSmallest(root, 1));
        // root = [3,1,4,null,2], k = 1
        // return 1
    }

    @Test 
    public void test2() {
        root = new TreeNode(5);
        root.setLeft(new TreeNode(3, new TreeNode(2), new TreeNode(4)));
        root.getLeft().getLeft().setLeft(new TreeNode(1));
        root.setRight(new TreeNode(6));

        assertEquals(3, solution.kthSmallest(root, 3));

        // root = [5,3,6,2,4,null,null,1], k = 3
        // return 3
    }
}
