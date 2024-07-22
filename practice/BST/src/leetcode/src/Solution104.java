package leetcode.src;

public class Solution104 {

    private int recursivelyCountDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
    
        int left_height = recursivelyCountDepth(node.left);
        int right_height = recursivelyCountDepth(node.right);
        
        if (left_height > right_height) {
            return 1 + left_height;
        } else {
            return 1 + right_height;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
    
        return recursivelyCountDepth(root);
    }
}
