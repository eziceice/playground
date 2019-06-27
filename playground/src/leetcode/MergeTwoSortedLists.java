package leetcode;

/**
 * Created by Ryan on 2017/5/15.
 */
public class MergeTwoSortedLists {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        /**
         * 使用递归
         */
        ListNode newHead;
        if (l1.val < l2.val) {
            newHead = l1;
            newHead.next = mergeTwoLists(l1.next,l2);
        } else {
            newHead = l2;
            newHead.next = mergeTwoLists(l1,l2.next);
        }
        return newHead;
    }
}
