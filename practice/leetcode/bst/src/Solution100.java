package leetcode.src;

public class Solution100 {
    /**
     * Given the roots of two binary trees p and q,check if they are the same or not.
     * (Nodes in the same position should have the same value)
     * 
     * @param p root of BT 1
     * @param q root of BT 2
     * @return true if 2 trees are identical, false otherwise
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null && q != null) {
            return false;
        }

        if (p != null && q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        else {
            boolean isLeftTreeSimilar = isSameTree(p.left, q.left);
            boolean isRightTreeSimilar = isSameTree(p.right, q.right);
            if (!isLeftTreeSimilar || !isRightTreeSimilar) {
                return false;
            }
        }
        return true;
    }
}
