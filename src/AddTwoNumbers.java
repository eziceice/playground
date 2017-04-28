/**
 * Created by Ryan on 2017/4/26.
 */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode first = listNode;
        int increment = 0;
        int v = 0;
        int w = 0;
        while (l1 != null || l2 != null || increment != 0) {
            ListNode cur = new ListNode(0);
            if (l1 == null)
                v = 0;
            else
                v = l1.val;
            if (l2 == null)
                w = 0;
            else
                w = l2.val;
            int sum = v + w + increment;
            cur.val = sum%10;
            increment = sum/10;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
            first.next = cur;
            first = cur;
        }
        return listNode.next;
    }


    public static void main(String[] args) {
        /*
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(4);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        listNode4.next = listNode5;
        ListNode listNode6 = new ListNode(4);
        listNode5.next = listNode6;

        addTwoNumbers(listNode,listNode4);
        */

        ListNode listNode = new ListNode(5);
        ListNode listNode2 = new ListNode(5);
        addTwoNumbers(listNode,listNode2);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
