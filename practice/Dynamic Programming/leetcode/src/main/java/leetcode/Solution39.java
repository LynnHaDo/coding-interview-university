package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution39 {

    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    private void backtrack(int start, int[] candidates, List<Integer> currentList, int total, int target) {
        if (total == target) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        if (total > target || start >= candidates.length) {
            return;
        }

        currentList.add(candidates[start]);
        //System.out.println("Branch with element " + candidates[start] + ": " + currentList);
        backtrack(start, candidates, currentList, total + candidates[start], target);
        currentList.remove(currentList.size() - 1);
        backtrack(start+1, candidates, currentList, total, target);
        //System.out.println("Branch without element " + candidates[start] + ": " + currentList);
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(0, candidates, new ArrayList<Integer>(), 0, target);
        return result;
    }
}