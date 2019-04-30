package algorithms4.Search;

/**
 * Created by Ryan on 2017/4/12.
 */
public class LinkedListMapSearch<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key,val,first);
    }

    public int size() {
        int size = 0;
        for (Node x = first; x != null; x = x.next)
            size++;
        return size;
    }

    public boolean delete(Key key) {
        if (first == null)
            return false;

        if (first.key.equals(key)) {
            if (first.next != null)
                first = first.next;
            else
                first = null;
            return true;
        }

        Node curNode = first.next;
        Node preNode = first;

        while (!curNode.key.equals(key)) {
            preNode = curNode;
            curNode = curNode.next;
            if (curNode == null)
                return false;
        }

        preNode.next = curNode.next;
        return true;
    }
}
