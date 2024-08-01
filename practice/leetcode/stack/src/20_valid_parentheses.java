import java.util.Stack;

class Solution20 {

    public boolean isValid(String s) {
        if (s.length() == 1) {
            return true;
        }

        String OPEN = "([{";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // Open bracket
            if (OPEN.indexOf((c)) > -1) {
                stack.push(c);
            } 
            // Closing bracket
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                else if (c == ')' && stack.peek() != '(') {
                    return false;
                }
                else if (c == ']' && stack.peek() != '[') {
                    return false;
                }
                else if (c == '}' && stack.peek() != '{') {
                    return false;
                }

                stack.pop();
            }
        }

        // Extra open tags
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution20 solution = new Solution20();

        String s = "()"; // true
        String s1 = "()[]{}"; // true
        String s2 = "){"; // false

        System.out.println(solution.isValid(s));
        System.out.println(solution.isValid(s1));
        System.out.println(solution.isValid(s2));
    }
}