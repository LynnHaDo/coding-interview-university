package src.leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution143 {
    private int size(ListNode head) {
        ListNode cur = head;
        int size = 0;
        for (; cur != null; cur = cur.next) {
            size++;
        }
        return size;
    }
    // Non-optimal
    public void reorderList(ListNode head) {
        int size = size(head);

        if (size == 1) {
            return;
        }

        ListNode cur = head;
        ListNode[] arr = new ListNode[size];

        for (int i = 0; cur != null; cur = cur.next, i++) {
            arr[i] = cur;
        }

        
        ListNode temp = head;
        ListNode next = head;

        for (int j = 0; j < size/2; j++) {
            next = arr[size - 1 - j];
            temp.next = next;
            next.next = arr[j+1];
            temp = arr[j+1];
        }
    }
}
