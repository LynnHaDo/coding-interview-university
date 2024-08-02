package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class Solution15Test {
    Solution15 solution;

    @Before
    public void setup() {
        solution = new Solution15();
    }

    @Test
    public void test1() {
        /**
         * Input: nums = [-1,0,1,2,-1,-4]
         * Output: [[-1,-1,2],[-1,0,1]]
         * Explanation:
         * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
         * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
         * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
         * The distinct triplets are [-1,0,1] and [-1,-1,2].
         * Notice that the order of the output and the order of the triplets does not
         * matter.
         */

        int[] nums = {-1,0,1,2,-1,-4};
        // -4 -1 -1 0 1 2 
        List<Integer> triplet1 = new ArrayList<Integer>(){{
            add(-1);
            add(-1);
            add(2);
        }};

        List<Integer> triplet2 = new ArrayList<Integer>(){{
            add(-1);
            add(0);
            add(1);
        }};
        List<List<Integer>> expected = new ArrayList<List<Integer>>();
        
        expected.add(triplet1);
        expected.add(triplet2);

        assertEquals(2, solution.threeSum(nums).size());
        
        for (int i = 0; i < expected.size(); i++) {

        }
        
    }

    @Test
    public void test2() {
        /**
         * Input: nums = [0,1,1]
         * Output: []
         * Explanation: The only possible triplet does not sum up to 0.
         */
        int[] nums = {0, 1, 1};

        assertEquals(0, solution.threeSum(nums).size());
    }

    @Test
    public void test3() {
        /**
         * Input: nums = [0,0,0]
         * Output: [[0,0,0]]
         * Explanation: The only possible triplet sums up to 0.
         */
        int[] nums = {0, 0, 0};
        List<List<Integer>> res = solution.threeSum(nums);
        assertEquals(1, res.size());
        
        List<Integer> numsList = new ArrayList<Integer>() {
            {
                add(0);
                add(0);
                add(0);
            }
        };

        assertArrayEquals(numsList.toArray(), res.get(0).toArray());
    }

    @Test
    public void test4() {
        /**
         * Input: nums = [0,0,0]
         * Output: [[0,0,0]]
         * Explanation: The only possible triplet sums up to 0.
         */
        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> res = solution.threeSum(nums);
        assertEquals(1, res.size());
        
        List<Integer> numsList = new ArrayList<Integer>() {
            {
                add(0);
                add(0);
                add(0);
            }
        };

        assertArrayEquals(numsList.toArray(), res.get(0).toArray());
    }
}
