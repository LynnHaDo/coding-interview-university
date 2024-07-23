package leetcode.src;

public class Solution572 {
    private boolean isSame(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null && subRoot != null) {
            return false;
        }

        if (root != null && subRoot == null) {
            return false;
        }

        if (subRoot.val == root.val) {
            return isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
        }

        return false;
    }
    /**
     * Given the roots of two binary trees root and subRoot, 
     * 
     * A subtree of a binary tree tree is a tree that consists 
     * of a node in tree and all of this node's descendants. 
     * The tree tree could also be considered as a subtree of itself.
     * 
     * @param root root of the original tree
     * @param subRoot root of the subtree to check for
     * @return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null && subRoot != null) {
            return false;
        }

        if (root != null && subRoot == null) {
            return true;
        }


        if (isSame(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}
