package test.dp;

import src.dp.Solution39;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class Solution39Test {
    
    @Test 
    public void test1() {
        Solution39 solution = new Solution39();
        int[] candidates = {2,3,6,7};
        List<Integer> list1 = new ArrayList<>() {
            {
                add(2);
                add(2);
                add(3);
            }
        };

        List<Integer> list2 = new ArrayList<>() {
            {
                add(7);
            }
        };
        
        List<List<Integer>> expectedResult = new ArrayList<List<Integer>>(){
            {
                add(list1);
                add(list2);
            }
        };

        assertArrayEquals(expectedResult.toArray(), solution.combinationSum(candidates, 7).toArray());
    }
}
