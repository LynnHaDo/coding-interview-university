package leetcode.test;

import leetcode.src.Solution572;
import leetcode.src.TreeNode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution572Test {
    Solution572 solution;
    TreeNode root1, subtree, root2;
    TreeNode root3, subtree3;

    @Before 
    public void setup() {
        this.solution = new Solution572();
        subtree = new TreeNode(4, new TreeNode(1), new TreeNode(2));
    }

    @Test 
    public void test1() {
        // root = [3,4,5,1,2], subRoot = [4,1,2]
        // Output: true
       root1 = new TreeNode(3, new TreeNode(4), new TreeNode(5));
       root1.getLeft().setLeft(new TreeNode(1));
       root1.getLeft().setRight(new TreeNode(2));
       assertTrue(solution.isSubtree(root1, subtree));
    }

    @Test 
    public void test2() {
        // root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2] 
        // Output: false
        root2 = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        root2.getLeft().setLeft(new TreeNode(1));
        root2.getLeft().setRight(new TreeNode(2));
        root2.getLeft().getRight().setRight(new TreeNode(0));

        assertFalse(solution.isSubtree(root2, subtree));
    }

    @Test 
    public void test3() {
        assertTrue(solution.isSubtree(new TreeNode(1, new TreeNode(1), null), 
                    new TreeNode(1)));
    }
}
