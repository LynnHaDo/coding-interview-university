package leetcode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution235Test {
    Solution235 solution;
    TreeNode root;

    @Before 
    public void setup() {
        solution = new Solution235();

        root = new TreeNode(6);
        root.setLeft(new TreeNode(2, new TreeNode(0), new TreeNode(4)));
        root.setRight(new TreeNode(8, new TreeNode(7), new TreeNode(9)));
        root.getLeft().setRight(new TreeNode(4, new TreeNode(3), new TreeNode(5)));
    }

    @Test 
    public void test1() {
        // root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        // output = 6
        assertSame(root.getLeft(), solution.lowestCommonAncestor(root, root.getLeft().getLeft(), root.getLeft().getRight()));
        //assertSame(root.getLeft(), solution.findParent(root.getLeft(), root.getLeft().getLeft(), null));
        //assertFalse(solution.isSuccessor(root.getLeft().getRight().getRight(), root.getLeft().getRight().getLeft()));
    }

    @Test 
    public void test2() {
        // root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        // output = 2
        assertSame(root.getLeft(), solution.lowestCommonAncestor(root, root.getLeft(), root.getLeft().getRight()));
    }

    @Test 
    public void test3() {
        // root = [2,1], p = 2, q = 1
        // output = 2
        TreeNode root = new TreeNode(2, new TreeNode(1), null);
        assertSame(root, solution.lowestCommonAncestor(root, root, root.getLeft()));
    }
}
