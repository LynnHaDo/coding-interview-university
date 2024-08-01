package leetcode.test;

import leetcode.src.Solution105;
import leetcode.src.TreeNode;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Solution105Test {
    Solution105 solution;
    ArrayList<Integer> preorderTraversalResult;
    
    public void getPreOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        preorderTraversalResult.add(root.getValue());
        getPreOrderTraversal(root.getLeft());
        getPreOrderTraversal(root.getRight());
    }

    @Before 
    public void setup() {
        solution = new Solution105();
        this.preorderTraversalResult = new ArrayList<>();
    }

    @Test 
    public void test1() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        Integer[] expected = {3,9,20,15,7};
        // Expected: [3,9,20,null,null,15,7]
        this.getPreOrderTraversal(solution.buildTree(preorder, inorder));
        assertArrayEquals(this.preorderTraversalResult.toArray(), expected);
    }

    @Test 
    public void test2() {
        int[] preorder = {-1};
        int[] inorder = {-1};
        // Expected: [-1]
        Integer[] expected = {-1};
        this.getPreOrderTraversal(solution.buildTree(preorder, inorder));
        assertArrayEquals(this.preorderTraversalResult.toArray(), expected);
    }
}
