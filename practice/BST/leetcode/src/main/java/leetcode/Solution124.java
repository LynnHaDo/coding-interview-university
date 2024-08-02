package leetcode;

public class Solution124 {
    private int result = Integer.MIN_VALUE;

    private int maxPathSumRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMax = maxPathSumRecursive(root.left);
        int rightMax = maxPathSumRecursive(root.right);

        leftMax = Math.max(leftMax, 0); // adding leftMax is optional (hence 0)
        rightMax = Math.max(rightMax, 0); 

        // Take the path from left -> center -> right
        this.result = Math.max(result, root.val + leftMax + rightMax);

        // Take each path individually
        return root.val + Math.max(leftMax, rightMax);
    }

    /**
     * A path in a binary tree is a sequence of nodes where each pair of adjacent 
     * nodes in the sequence has an edge connecting them. A node can only appear 
     * in the sequence at most once. Note that the path does not need to pass 
     * through the root.
     * The path sum of a path is the sum of the node's values in the path.
     * Given the root of a binary tree, return the maximum path sum of any non-empty path.
     * 
     * @param root root of the tree
     * @return maximum path sum
     */
    public int maxPathSum(TreeNode root) {
        maxPathSumRecursive(root);
        return result;
    }
}