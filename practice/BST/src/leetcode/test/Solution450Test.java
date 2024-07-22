package leetcode.test;

import leetcode.src.Solution450;
import leetcode.src.TreeNode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Solution450Test {
    Solution450 solution;
    TreeNode root;
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
        solution = new Solution450();
        inorderTraversalResult = new ArrayList<>();

        // root = [5,3,6,2,4,null,7], key = 3
        root = new TreeNode(5, new TreeNode(3), new TreeNode(6));
        root.getLeft().setLeft(new TreeNode(2));
        root.getLeft().setRight(new TreeNode(4));
        root.getRight().setRight(new TreeNode(7));
    }

    @Test 
    public void testDeleteEmptyTree() {
        assertNull(solution.deleteNode(new TreeNode(), 0));
    }

    @Test 
    public void testDeleteNonexistentNode() {
        Integer[] expectedResult = {2, 3, 4, 5, 6, 7};
        solution.deleteNode(root, 0);
        getInOrderTraversal(root);

        assertArrayEquals(inorderTraversalResult.toArray(), expectedResult);
    }

    @Test 
    public void testDeleteNodeWithTwoChildren_1() {
        Integer[] expectedResult = {2, 4, 5, 6, 7};
        solution.deleteNode(root, 3);
        getInOrderTraversal(root);

        assertArrayEquals(inorderTraversalResult.toArray(), expectedResult);
    }

    @Test 
    public void testDeleteNodeWithTwoChildren_2() {
        Integer[] expectedResult = {2, 3, 4, 6, 7};
        solution.deleteNode(root, 5);
        getInOrderTraversal(root);

        assertArrayEquals(inorderTraversalResult.toArray(), expectedResult);
    }

    @Test 
    public void testDeleteLeafNode() {
        Integer[] expectedResult = {3, 4, 5, 6, 7};
        solution.deleteNode(root, 2);
        getInOrderTraversal(root);

        assertArrayEquals(inorderTraversalResult.toArray(), expectedResult);
    }

    @Test 
    public void testDeleteNodeWithOneChild() {
        Integer[] expectedResult = {2, 3, 4, 5, 7};
        solution.deleteNode(root, 6);
        getInOrderTraversal(root);

        assertArrayEquals(inorderTraversalResult.toArray(), expectedResult);
    }
}
