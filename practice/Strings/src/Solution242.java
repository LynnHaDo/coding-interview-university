import java.util.HashMap;
import java.util.Set;

public class Solution242 {
    // Less efficient
    public boolean isAnagramNoGood(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        if (s.length() == 1 && !s.equals(t)) {
            return false;
        }

        if (s.equals(t)) {
            return true;
        }

        HashMap<Character, Integer> s_map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (s_map.containsKey(s.charAt(i))) {
                s_map.put(s.charAt(i), s_map.get(s.charAt(i)) + 1);
                continue;
            }
            s_map.put(s.charAt(i), 1);
        }

        int left = 0;

        Set<Character> chars = s_map.keySet();

        for (Character c : chars) {
            int count = 0;
            while (t.indexOf(c.toString(), left) >= 0 && left < t.length()) {
                count++;
                left = t.indexOf(c.toString(), left) + 1;
            }

            if (count != s_map.get(c)) {
                return false;
            }

            left = 0;
        }

        return true;
    }

    // Better
        // Space: O(26) for the map to contain at max 26 lowercase letters
        // Time: O(2n) = O(n)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        if (s.length() == 1 && !s.equals(t)) {
            return false;
        }

        if (s.equals(t)) {
            return true;
        }

        HashMap<Character, Integer> s_map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (s_map.containsKey(s.charAt(i))) {
                s_map.put(s.charAt(i), s_map.get(s.charAt(i)) + 1);
                continue;
            }
            s_map.put(s.charAt(i), 1);
        }

        for (int i = 0; i < t.length(); i++) {
            if (s_map.containsKey(t.charAt(i))) {
                if (s_map.get(t.charAt(i)) == 1) {
                    s_map.remove(t.charAt(i));
                } else {
                    s_map.put(t.charAt(i), s_map.get(t.charAt(i)) - 1);
                }
            }
            else {
                return false;
            }
        }

        return s_map.isEmpty();
    }

    public static void main(String[] args) {
        Solution242 solution = new Solution242();
        String s = "anagram";
        String t = "nagaram";

        System.out.println(solution.isAnagramNoGood(s, t));
        System.out.println(solution.isAnagram(s, t));
    }
}
