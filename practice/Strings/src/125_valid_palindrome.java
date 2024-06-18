class Solution125 {
    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }

        

        return true;
    }

    public static void main(String[] args) {
        Solution125 solution = new Solution125();

        String s = "A man, a plan, a canal: Panama";
        String s1 = "race a car";
        String s2 = " ";

        System.out.println(solution.isPalindrome(s));
        System.out.println(solution.isPalindrome(s1));
        System.out.println(solution.isPalindrome(s2));
    }
}
