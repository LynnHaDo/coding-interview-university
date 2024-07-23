package leetcode.src;

public class Solution572 {
    private boolean isSubtreeRecursive(TreeNode root, TreeNode subRoot, boolean isRootFound) {
        if (root == null && subRoot == null) {
            return true;
        }

        if (root == null && subRoot != null) {
            return false;
        }

        if (root != null && subRoot == null) {
            return isRootFound;
        }

        if (subRoot.val == root.val) {
            boolean isLeftSubtree = isSubtreeRecursive(root.left, subRoot.left, true);
            boolean isRightSubtree = isSubtreeRecursive(root.right, subRoot.right, true);

            return isLeftSubtree && isRightSubtree;
        }

        return isSubtreeRecursive(root.left, subRoot, false) 
            || isSubtreeRecursive(root.right, subRoot, false);
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
        return isSubtreeRecursive(root, subRoot, false);
    }
}
