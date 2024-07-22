package leetcode.src;

import java.util.ArrayList;

public class Solution98 {
    /**
     * Conduct inorder traversal on a tree
     * @param node root node
     * @param output inorder traversal result
     */
    private void inorderTraversal(TreeNode node, ArrayList<Integer> output) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left, output);
        output.add(node.val);
        inorderTraversal(node.right, output);
    }
    
    /**
     * Check if a tree is a valid BST
     * @param root root of the tree
     * @return true if the tree is valid, false otherwise
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        ArrayList<Integer> inorderTraversalResult = new ArrayList<>();
        
        // Conduct in order traversal on the tree
        inorderTraversal(root, inorderTraversalResult);

        for (int i = 0; i < inorderTraversalResult.size() - 1; i++) {
            if (inorderTraversalResult.get(i) >= inorderTraversalResult.get(i+1)) {
                return false;
            }
        }

        return true;
    }
}
