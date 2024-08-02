package leetcode;
import java.util.HashSet;

public class Solution3 {
    // Sliding window
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        } 
        
        int maxLength = 0;
        int left = 0;
        int right = 0;

        HashSet<Character> subString = new HashSet<>();

        for (; right < s.length() ; right++) {
            // Continue removing until the set only contains unique characters
            while (subString.contains(s.charAt(right))) {
                subString.remove(s.charAt(left));
                left++;
            } 
            subString.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        String str = "dvdf";
        System.out.println(solution.lengthOfLongestSubstring(str));
    }
}
