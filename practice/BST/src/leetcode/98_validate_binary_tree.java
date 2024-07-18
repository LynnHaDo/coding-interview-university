package leetcode;

class Solution98 {
    /**
     * Fail: [5, 4, 6, null, null, 3, 7]
     * @param left
     * @param cur
     * @param right
     * @return
     */
    private boolean isValidTrio(TreeNode left, TreeNode cur, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null && right != null) {
            return cur.val < right.val;
        }

        if (right == null && left != null) {
            return cur.val > left.val;
        }

        if (cur.val >= right.val || cur.val <= left.val) {
            return false;
        }

        return true;
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

        if (isValidTrio(root.left, root, root.right)) {
            return isValidBST(root.left) && isValidBST(root.right);
        }

        return false;
    }
}
