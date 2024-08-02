package leetcode;

import java.util.Arrays;

public class Solution105 {

    /**
     * Construct and return the binary tree based on preorder and inorder traversal
     * results
     * 
     * Constraints:
     * 
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * preorder and inorder consist of unique values.
     * Each value of inorder also appears in preorder.
     * preorder is guaranteed to be the preorder traversal of the tree.
     * inorder is guaranteed to be the inorder traversal of the tree.
     * 
     * @param preorder preorder traversal of a binary tree (self, left, right)
     * @param inorder  inorder traversal of the same tree (left, self, right)
     * @return root of the tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        // Root is the first element in preorder array
        TreeNode root = new TreeNode(preorder[0]);

        // Tree with 1 node
        if (preorder.length == 1) {
            return root;
        }

        // Find the cutoff that divides the left and right subtrees
        int mid = 0;
        while (true) {
            if (inorder[mid] == root.val) {
                break;
            }
            mid++;
        }

        // Build left subtree
        root.left = buildTree(  Arrays.copyOfRange(preorder, 1, mid+1), 
                                Arrays.copyOfRange(inorder, 0, mid));
        // Right subtree
        root.right = buildTree( Arrays.copyOfRange(preorder, mid + 1, preorder.length), 
                                Arrays.copyOfRange(inorder, mid + 1, inorder.length));

        return root;
    }
}
