package src.leetcode;

public class Solution19 {
    /*
     * Assume list is not empty
     */
    private int size(ListNode head) {
        int size = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            size++;
        }
        return size;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = this.size(head);

        if (size == 1 && n > 1) {
            return head;
        }

        int index = size - n;

        if (index == 0) {
            head = head.next;
            return head;
        }

        ListNode cur = head;
        int c = 0;

        while (cur != null) {
            if (c == index - 1) {
                cur.next = cur.next.next;
                break;
            }
            c++;
            cur = cur.next;
        }

        return head;
    }
}
