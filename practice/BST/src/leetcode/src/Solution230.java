package leetcode.src;

public class Solution230 {
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
        return root.val;
    }
}