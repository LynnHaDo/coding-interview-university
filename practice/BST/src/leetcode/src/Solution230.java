package leetcode.src;

import java.util.List;
import java.util.ArrayList;

public class Solution230 {
    private List<Integer> inorderTraversal;

    private void inorderTraverse(TreeNode root, int k) {
        if (root == null || inorderTraversal.size() == k) {
            return;
        }

        inorderTraverse(root.left, k);
        inorderTraversal.add(root.val);
        inorderTraverse(root.right, k);
    }

    /**
     * Given the root of a binary search tree, 
     * and an integer k, return the kth smallest value 
     * (1-indexed) of all the values of the nodes in the tree.
     * 
     * Constraints: 
     * 1 <= k <= n <= 104
     * 
     * @param root root of the tree
     * @param k kth smallest number is the position to return
     * @return the kth smallest value (1-indexed) of all the values 
     * of the nodes in the tree.
     */
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal = new ArrayList<>();
        
        inorderTraverse(root, k);

        return inorderTraversal.get(k-1);
    }
}