package leetcode;

import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Solution102 {
    private List<List<TreeNode>> levelOrderTree;
    private List<List<Integer>> levelOrder;

    /**
     * Given the root of a binary tree, 
     * return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     * @param root root of the tree
     * @return list of list containing elements in each level
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        levelOrder = new ArrayList<List<Integer>>();
        levelOrderTree = new ArrayList<List<TreeNode>>();

        if (root == null) {
            return levelOrder;
        }

        Queue<TreeNode> q = new LinkedList<>();
        // Initialize queue
        q.offer(root);

        // Add the first level
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(root.val);   
        levelOrder.add(temp);
        
        List<TreeNode> tempTree = new ArrayList<TreeNode>();
        tempTree.add(root);
        levelOrderTree.add(tempTree);
        
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            List<Integer> level = new ArrayList<Integer>();
            List<TreeNode> levelTree = new ArrayList<TreeNode>();
            
            if (levelOrder.size() > 1 
                && levelOrderTree.get(levelOrderTree.size() - 2).contains(node)) {
                level = levelOrder.remove(levelOrder.size() - 1);
                levelTree = levelOrderTree.remove(levelOrderTree.size() - 1);
            }
            

            if (node == null) {
                continue;
            }

            if (node.left != null) {
                q.add(node.left);
                level.add(node.left.val);
                levelTree.add(node.left);
            }

            if (node.right != null) {
                q.add(node.right);
                level.add(node.right.val);
                levelTree.add(node.right);
            }

            // No children
            if (level.size() == 0) {
                continue;
            }

            levelOrder.add(level);
            levelOrderTree.add(levelTree);
        }

        return levelOrder;
    }
}
