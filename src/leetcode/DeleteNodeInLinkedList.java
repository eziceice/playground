package leetcode;

/**
 * Created by Ryan on 2017/5/3.
 */
public class DeleteNodeInLinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 因为无法链接到该node的前结点，可以将该node的next复制到该node然后将next删除
     *
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
