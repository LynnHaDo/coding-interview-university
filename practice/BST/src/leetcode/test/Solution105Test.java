package leetcode.test;

import leetcode.src.Solution105;
import leetcode.src.TreeNode;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Solution105Test {
    Solution105 solution;
    ArrayList<Integer> inorderTraversalResult;
    
    public void getInOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        getInOrderTraversal(root.getLeft());
        inorderTraversalResult.add(root.getValue());
        getInOrderTraversal(root.getRight());
    }

    @Before 
    public void setup() {
        solution = new Solution105();
    }

    @Test 
    public void test1() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        // Expected: [3,9,20,null,null,15,7]
    }

    @Test 
    public void test2() {
        int[] preorder = {-1};
        int[] inorder = {-1};
        // Expected: [-1]
    }
}
