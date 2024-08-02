package leetcode;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Solution102Test {
    Solution102 solution;
    
    @Before
    public void setup() {
        solution = new Solution102();
    }

    @Test 
    public void test1() {
        // root = [3,9,20,null,null,15,7]
        // Expected: [[3],[9,20],[15,7]]

        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20));
        root.getRight().setLeft(new TreeNode(15));
        root.getRight().setRight(new TreeNode(7));        

        List<Integer> levelOne = new ArrayList<Integer>() {{
            add(3);
        }};

        List<Integer> levelTwo = new ArrayList<Integer>() {{
            add(9);
            add(20);
        }};

        List<Integer> levelThree = new ArrayList<Integer>() {{
            add(15);
            add(7);
        }};

        List<List<Integer>> expected = new ArrayList<List<Integer>>() {};

        expected.add(levelOne);
        expected.add(levelTwo);
        expected.add(levelThree);

        assertArrayEquals(expected.toArray(), solution.levelOrder(root).toArray());
    }

    @Test 
    public void test2() {
        // root = [1]
        // Expected: [[1]]
        TreeNode root = new TreeNode(1);

        List<Integer> levelOne = new ArrayList<Integer>() {
            {
                add(1);
            }
        };

        List<List<Integer>> expected = new ArrayList<List<Integer>>();
        expected.add(levelOne);

        assertArrayEquals(solution.levelOrder(root).toArray(), expected.toArray());
    }

    @Test 
    public void test3() {
        // root = []
        // Expected: []

        List<List<Integer>> expected = new ArrayList<List<Integer>>();

        //assertArrayEquals(expected.toArray(), solution.levelOrder(null).toArray());
    }

    @Test
    public void test4() {
        // [1,2,3,4,null,null,5]
        // Expected: [[1],[2,3],[4,5]]
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(4));
        root.getRight().setRight(new TreeNode(5));        

        List<Integer> levelOne = new ArrayList<Integer>() {{
            add(1);
        }};

        List<Integer> levelTwo = new ArrayList<Integer>() {{
            add(2);
            add(3);
        }};

        List<Integer> levelThree = new ArrayList<Integer>() {{
            add(4);
            add(5);
        }};

        List<List<Integer>> expected = new ArrayList<List<Integer>>();
        expected.add(levelOne);
        expected.add(levelTwo);
        expected.add(levelThree);

        assertArrayEquals(expected.toArray(), solution.levelOrder(root).toArray());
    }
}
