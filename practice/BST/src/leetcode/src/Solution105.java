package leetcode.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution105 {
    private TreeNode recursivelyBuildTree(int[] preorder, int[] inorder, int preorderStart, int preorderEnd,
            int inorderStart, int inorderEnd) {
        if (preorderEnd - preorderStart + 1 <= 0 || inorderEnd - inorderStart + 1 <= 0) {
            return null;
        }

        TreeNode center = new TreeNode(preorder[preorderStart]);
        // Find the cutoff that divides the left and right subtrees
        int mid = inorderStart;
        for (;; mid++) {
            if (inorder[mid] == center.val) {
                break;
            }
        }

        // Build left subtree
        center.left = recursivelyBuildTree(preorder, inorder, 1, mid - 1, 0, mid -1);
        center.right = recursivelyBuildTree(preorder, inorder, mid+1, preorder.length - 1, mid+1, inorder.length);
        return center;
    }

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
        // Root is the first element in preorder array
        TreeNode root = new TreeNode(preorder[0]);

        // Tree with 1 node
        if (preorder.length == 1) {
            return root;
        }

        // Find the cutoff that divides the left and right subtrees
        int mid = 0;
        for (;; mid++) {
            if (inorder[mid] == root.val) {
                break;
            }
        }

        // Build left subtree
        root.left = recursivelyBuildTree(preorder, inorder, 1, mid - 1, 0, mid -1);
        root.right = recursivelyBuildTree(preorder, inorder, mid+1, preorder.length - 1, mid+1, inorder.length);
        return root;
    }
}
