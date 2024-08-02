package leetcode;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class Solution104Test {
    Solution104 solution;
    TreeNode root1;
    TreeNode root2; 

    @Before 
    public void setup() {
        solution = new Solution104();

        // [3,9,20,null,null,15,7] : depth 3
        root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20));
        root1.getRight().setLeft(new TreeNode(15));
        root1.getRight().setRight(new TreeNode(7));
        // [1,null,2] : depth 2
        root2 = new TreeNode(1, null, new TreeNode(2));
    }

    @Test 
    public void test1() {
        assertEquals(solution.maxDepth(root1), 3);
    }

    @Test 
    public void test2() {
        assertEquals(solution.maxDepth(root2), 2);
    }

    @Test 
    public void test3() {
        // Tree with only 1 node
        assertEquals(solution.maxDepth(new TreeNode(0)), 1); 
    }
}
