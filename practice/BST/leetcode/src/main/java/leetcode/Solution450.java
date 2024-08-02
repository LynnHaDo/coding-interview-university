package leetcode;

public class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }

        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        else {
            if (root.left == null && root.right == null) {
                return null;
            }
            else if (root.left == null && root.right != null) {
                return root.right;
            }
            else if (root.left != null && root.right == null) {
                return root.left;
            }
            else {
                TreeNode temp = root.right;

                while (temp.left != null) {
                    temp = temp.left;
                }

                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        }

        return root;
    }
}
