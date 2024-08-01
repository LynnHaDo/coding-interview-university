package leetcode.src;

/**
 * Represents a node in the binary tree
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) { 
        this.val = val; 
    }
    
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return this.val;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public void setValue(int val) {
        this.val = val;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
