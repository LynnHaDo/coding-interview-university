package leetcode;

class Solution450 {
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

    private void printTree(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        Solution450 solution = new Solution450();
        // root = [5,3,6,2,4,null,7], key = 3
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        solution.deleteNode(root, 5);
        solution.printTree(root);
    }
}
