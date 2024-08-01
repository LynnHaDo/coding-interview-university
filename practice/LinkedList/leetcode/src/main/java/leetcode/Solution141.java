package src.leetcode;

import java.util.HashSet;

public class Solution141 {
    // Less efficient in terms of space and time
        // Space: O(n) -- need a set
        // Time: O(n) -- Loop through each element. 
            // contains() method: unsure how long it takes; depends on the hashing process
    public boolean hasCycle(ListNode head) {
        // empty or 1 element
        if (head == null || head.next == null) {
            return false;
        }

        ListNode cur = head;
        HashSet<ListNode> set = new HashSet<>();

        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            }
            set.add(cur);
            cur = cur.next;
        }

        return false;
    }

    // More efficient
    public boolean hasCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow.equals(fast)) {
                return true;
            }
        }

        return false;
    }
}
