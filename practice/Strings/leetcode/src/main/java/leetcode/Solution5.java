package leetcode;

public class Solution5 {
    /**
     * Given a string s, return the longest palindromic substring in s.
     * @param s string to search for the longest palindromic substring
     * @return 
     */
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        } 

        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1)? 
                    s : Character.toString(s.charAt(0)); 
        }

        String longestPalindrome = Character.toString(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            int start = 0;

            while (start <= i) {
                int end =  2 * i + 1 - start;
                // Odd: character i is the middle
                if (s.substring(start, i).equals(new StringBuilder(s.substring(i+1, end)).reverse().toString())) {
                    longestPalindrome = 2*(i - start)+1 > longestPalindrome.length()? s.substring(i+1, 2 * i + 1 - start):longestPalindrome;
                }

                // Even
                if (s.substring(start, i+1).equals(new StringBuilder(s.substring(i+1, 2 * i + 2 - start)).reverse().toString())) {
                    longestPalindrome = 2 * i + 2 - 2 * start > longestPalindrome.length()? s.substring(i+1, 2 * i + 2 - start):longestPalindrome;
                }
                start++;
            }
        }

        return longestPalindrome;
    }
}
