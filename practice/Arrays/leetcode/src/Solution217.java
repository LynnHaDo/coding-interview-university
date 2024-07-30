package src;
import java.util.HashSet;

public class Solution217 {
    // O(n^2)
    public boolean bruteForce(int[] nums) {
        // 1-element array
        if (nums.length == 1){
            return false;
        }

        int start = 0;
        int end = 1;

        for (; end < nums.length && start < nums.length ; end++){
            if (nums[start] == nums[end]){
                return true;
            }
            if (end == nums.length - 1){
                end = start + 1;
                start++;
            }
        }

        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        // 1-element array
        if (nums.length == 1){
            return false;
        }

        // O(n)
        HashSet<Integer> list = new HashSet<>(); // good data structure for checking membership
        for (int num : nums) {
            if (list.contains(num)) {
                return true;
            }
            list.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        Solution217 solution = new Solution217();
        int[] arr = {1,1,1,3,3,4,3,2,4,2};

        System.out.println(solution.bruteForce(arr));
        System.out.println(solution.containsDuplicate(arr));
    }
}