package leetcode.test;

import leetcode.src.Solution100;
import leetcode.src.TreeNode;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution100Test {
    Solution100 solution;
    /** Case 1: p = [1,2,3], q = [1,2,3] */
    TreeNode case1Node1;
    TreeNode case1Node2;
    /** Case 2: p = [1,2], q = [1,null,2] */
    TreeNode case2Node1;
    TreeNode case2Node2;

    /** Case 3: p = [1,2,1], q = [1,1,2] */
    TreeNode case3Node1;
    TreeNode case3Node2;

    @Before
    public void setup() {
        solution = new Solution100();

        // p = [1,2,3], q = [1,2,3]: true
        case1Node1 = case1Node2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        // p = [1,2], q = [1,null,2]: false
        case2Node1 = new TreeNode(1);
        case2Node1.setLeft(new TreeNode(2));

        case2Node2 = new TreeNode(1, null, new TreeNode(2));
        // p = [1,2,1], q = [1,1,2]: false  
        case3Node1 = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        case3Node2 = new TreeNode(1, new TreeNode(1), new TreeNode(2));
    }

    @Test 
    public void test1() {
        assertTrue(solution.isSameTree(case1Node1, case1Node2));
    }

    @Test 
    public void test2() {
        assertFalse(solution.isSameTree(case2Node1, case2Node2));
    }

    @Test 
    public void test3() {
        assertFalse(solution.isSameTree(case3Node1, case3Node2));
    }
}
