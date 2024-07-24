package leetcode.src;

import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Solution102 {
    private List<List<Integer>> levelOrder;

    /**
     * Given the root of a binary tree, 
     * return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     * @param root root of the tree
     * @return list of list containing elements in each level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        levelOrder = new ArrayList<List<Integer>>();

        if (root == null) {
            return levelOrder;
        }

        Queue<TreeNode> q = new LinkedList<>();
        // Initialize queue
        q.offer(root);

        // Add the first level
        levelOrder.add(new ArrayList<Integer>() {
            {
                add(root.val);
            }
        });
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            List<Integer> level = new ArrayList<Integer>();

            if (levelOrder.size() > 1 && levelOrder.get(levelOrder.size() - 2).contains(node.val)) {
                level = levelOrder.get(levelOrder.size() - 1);
            }

            if (node == null) {
                continue;
            }

            if (node.left != null) {
                q.add(node.left);
                level.add(node.left.val);
            }

            if (node.right != null) {
                q.add(node.right);
                level.add(node.right.val);
            }

            // No children
            if (level.size() == 0) {
                continue;
            }

            levelOrder.add(level);
        }

        return levelOrder;
    }

    public static void main(String[] args) {
        Solution102 solution = new Solution102();

        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(4));
        root.getRight().setRight(new TreeNode(5));        

        List<Integer> levelOne = new ArrayList<>() {{
            add(1);
        }};

        List<Integer> levelTwo = new ArrayList<>() {{
            add(2);
            add(3);
        }};

        List<Integer> levelThree = new ArrayList<>() {{
            add(4);
            add(5);
        }};

        List<List<Integer>> expected = new ArrayList<>() {
            {
                add(levelOne);
                add(levelTwo);
                add(levelThree);
            }
        };

        System.out.println(Arrays.toString(solution.levelOrder(root).toArray()));
        System.out.println(Arrays.toString(expected.toArray()));
    }
}
