package leetcode;

public class Solution21 {
    /**
     * Merge the two lists into one sorted list
     * @param list1 sorted list 1
     * @param list2 sorted list 2
     * @return head of new linked list
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list3 = new ListNode();

        ListNode curr1 = list1;
        ListNode curr2 = list2;

        while (curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                list3.next = curr1;
            } else if (curr1.val > curr2.val) {
                list3.next = curr2;
            } else {
                list3.next = curr1;
                list3.next.next = curr2;
            }

            curr1 = curr1.next;
            curr2 = curr2.next;
            list3 = list3.next;
        }

        while (curr1 != null) {
            list3.next = curr1;
            curr1 = curr1.next;
            list3 = list3.next;
        }

        while (curr2 != null) {
            list3.next = curr2;
            curr2 = curr2.next;
            list3 = list3.next;
        }

        return list3;
    }
}
