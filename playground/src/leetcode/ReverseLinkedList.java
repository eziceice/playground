package leetcode;

/**
 * Created by Ryan on 2017/5/3.
 */
public class ReverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }

        ListNode[] listNodes = new ListNode[count];

        int i = 0;
        while (head != null) {
            listNodes[i] = head;
            head = head.next;
            i++;
        }

        ListNode result = listNodes[listNodes.length - 1];
        ListNode first = result;
        for (int j = listNodes.length - 2; j >= 0; j--) {
            first.next = listNodes[j];
            first = first.next;
        }
        first.next = null;
        return result;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null)
            return null;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        head.next = null;
        head = pre;
        return head;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        /**
        for (ListNode te = listNode; te != null; te = te.next) {
            System.out.println(te.val);
        }
         **/

        //ListNode result = reverseList(listNode);
        ListNode result = reverse(listNode);
        for (ListNode te = result; te != null; te = te.next) {
            System.out.println(te.val);
        }
    }
}
