package leetcode;

public class Solution235 {
    private TreeNode findParent(TreeNode root, TreeNode node, TreeNode parent) {
        if (root == null) {
            return null;
        }

        // If the 1st node's parent is found
        if (root.val == node.val) {
            return parent;
        }

        TreeNode leftParent = findParent(root.left, node, root);
        TreeNode rightParent = findParent(root.right, node, root);

        return leftParent == null? rightParent : leftParent;
    }

    public boolean isSuccessor(TreeNode node1, TreeNode node2) {
        if (node1 == null) {
            return false;
        }

        if (node1.equals(node2)) {
            return true;
        }

        return isSuccessor(node1.left, node2) || isSuccessor(node1.right, node2);
    }

    /**
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) node
     * of two given nodes in the BST.
     * 
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor
     * is defined between two nodes p and q as the lowest node in T that has both p
     * and q as descendants (where we allow a node to be a descendant of itself).”
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        boolean isQSucceedingP = isSuccessor(p, q);
        boolean isPSucceedingQ = isSuccessor(q, p);
        // If p or q is the LCA of the other
        if (isQSucceedingP) {
            return p;
        }

        if (isPSucceedingQ) {
            return q;
        }

        TreeNode pParent = findParent(root, p, null);

        while (!isSuccessor(pParent, q)) {
            pParent = findParent(root, pParent, null);
        }

        return pParent;
    }
}
