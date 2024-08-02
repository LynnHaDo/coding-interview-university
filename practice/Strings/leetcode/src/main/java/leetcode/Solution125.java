package leetcode;
public class Solution125 {
    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        
        /** 
        StringBuilder sb = new StringBuilder();

        // Filter out alphabetical characters
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }

        s = sb.toString().toLowerCase();
        System.out.println(s);

        if (s.length() == 0) {
            return true;
        }
        */

        int start = 0;
        int end = s.length() - 1;

        s = s.toLowerCase();

        while (start < end) {
            
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
                continue;
            }

            if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
                continue;
            }

            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution125 solution = new Solution125();

        String s = "A man, a plan, a canal: Panama";
        String s1 = "race a car";
        String s2 = "0P";

        System.out.println(solution.isPalindrome(s));
        System.out.println(solution.isPalindrome(s1));
        System.out.println(solution.isPalindrome(s2));
    }
}
