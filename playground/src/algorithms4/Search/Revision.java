package algorithms4.Search;

/**
 * Created by Ryan on 2017/4/22.
 */
public class Revision {
    public static void main(String[] args) {
    }
}

/**
 * 无序链表实现Map
 * @param <Key>
 * @param <Value>
 */

class LinkedListST<Key,Value> {
    Node first;
    class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Key key, Value value) {
        for (Node x = first; x.next != null; x = x.next) {
            if (x.key.equals(key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
    }

    public Value get(Key key) {
        if (first == null)
            return null;
        for (Node x = first; x.next != null; x = x.next) {
            if (x.key.equals(key))
                return x.value;
        }
        return null;
    }

    public boolean remove(Key key) {
        if (first == null)
            return false;
        if (first.key.equals(key)) {
            if (first.next == null)
                first = null;
            else
                first = first.next;
            return true;
        }

        Node preNode = first;
        Node curNode = first.next;
        while (curNode != null) {
            if (curNode.key.equals(key)) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
        }
        return false;
    }

    public int size() {
        int N = 0;
        for (Node x = first; x.next != null; x = x.next)
            N++;
        return N;
    }
}

/**
 * 有序数组实现Map
 * @param <Key>
 * @param <Value>
 */

class OrderArrayST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public OrderArrayST(int capacity) {
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        if (keys[i].equals(key)) {
            values[i] = value;
            return;
        }

        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }

        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        int i = rank(key);
        if (keys[i].equals(key)) {
            return values[i];
        }
        return null;
    }

    public boolean delete(Key key) {
        int i = rank(key);
        if (keys[i].equals(key)) {
            for (int j = i; j < N; j++) {
                keys[j] = keys[j+1];
                values[j] = values[j+1];
            }
            N--;
            return true;
        }
        return false;
    }


    public int rank(Key key) {
        int lo = 0;
        int hi = N-1;
        while (lo <= hi) {

            int mid = lo + (hi - lo)/2;
            int cmp = keys[mid].compareTo(key);

            if (cmp > 0)
                hi = mid - 1;
            else if (cmp < 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }
}

/**
 * 二叉查找树实现Map
 * @param <Key>
 * @param <Value>
 */

class BinaryST<Key extends Comparable<Key>, Value> {
    Node root;
    class Node {
        Key key;
        Value value;
        Node left,right;
        int N;
        public Node(Key key, Value value,int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public void put(Key key, Value value) {
        root = put(root,key,value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key,value,1);
        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            node.left = put(node.left,key,value);
        else if (cmp < 0)
            node.right = put(node.right,key,value);
        else
            node.value = value;
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    public Value get(Key key) {
        return get(root,key);
    }

    private Value get(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            return get(node.left,key);
        else if (cmp < 0)
            return get(node.right,key);
        else
            return node.value;
    }

    public int size() {
        return size(root);
    }
    private int size(Node node) {
        if (node == null)
            return 0;
        return node.N;
    }

    public void delete(Key key) {
        delete(root,key);
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            node.right = delete(node.right,key);
        else if (cmp < 0)
            node.left = delete(node.left,key);
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node h = node;
            node = min(node.right);
            node.right = deleteMin(h.right);
            node.left = h.left;
        }
        node.N = size(node.right) + 1 + size(node.left);
        return node;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }
}

/**
 * 红黑树实现Map - Java中TreeMap的实现
 * @param <Key>
 * @param <Value>
 */


class BalckRedST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    Node root;

    class Node {
        Key key;
        Value value;
        Node right,left;
        boolean color;
        int N;

        public Node(Key key, Value value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            N = n;
        }
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.N = node.N;
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }


    public void put(Key key,Value value) {
        root = put(root,key,value);

        /**
            根节点一定要在最后设置为黑色
         */
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key,value,RED,1);
        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            node.left = put(node.left,key,value);
        else if (cmp < 0)
            node.right = put(node.right,key,value);
        else
            node.value = value;
        if (isRed(node.right) && !isRed(node.left))
            rotateLeft(node);
        if (isRed(node.left.left) && isRed(node.left))
            rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColor(node);
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    public Value get(Key key) {
        return get(root,key);
    }

    private Value get(Node node,Key key) {
        if (node == null)
            return null;
        int cmp = node.key.compareTo(key);
        if (cmp > 0)
            return get(node.left,key);
        else if (cmp < 0)
            return get(node.right,key);
        else
            return node.value;
    }

    private void flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.color == RED;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return node.N;
    }
}

/**
 * 链表和数组实现hashMap - 拉链法 - Java中HashMap和HashSet的实现
 * @param <Key>
 * @param <Value>
 */

class LinkedListHashMap<Key, Value> {
    private int N; //键值对数量
    private int M; //数组长度
    private LinkedListST<Key,Value> st[];

    public LinkedListHashMap(int capacity) {
        st = new LinkedListST[capacity];
        M = capacity;
        for (int i = 0; i < st.length ; i++) {
            st[i] = new LinkedListST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key,value);
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public boolean remove(Key key) {
        return st[hash(key)].remove(key);
    }

    public int size() {
        int n = 0;
        for (int i = 0; i < st.length ; i++) {
            n += st[i].size();
        }
        return n;
    }
}

/**
 * 线性探测法-双数组Map
 * @param <Key>
 * @param <Value>
 */

class TwoArrayHashMap<Key,Value> {
    private Key[] keys;
    private Value[] values;
    private int M;//数组长度
    private int N;//键值对数量

    public TwoArrayHashMap(int capacity) {
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
        M = capacity;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        if (N > M/2)
            resize(M*2);
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (key.equals(keys[i]))
                return values[i];
        }
        return null;
    }

    public boolean remove(Key key) {
        if (!contains(key))
            return false;
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % M;
        keys[i] = null;
        values[i] = null;
        N--;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key reputKey = keys[i];
            Value reputValue = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(reputKey,reputValue);
            i = (i + 1) % M;
        }
        if (N > 0 & N == M/8)
            resize(M/2);
        return true;
    }

    private boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    private void resize(int capacity) {
        TwoArrayHashMap<Key,Value> twoArrayHashMap = new TwoArrayHashMap<>(capacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null)
                put(keys[i],values[i]);
        }
        keys = twoArrayHashMap.keys;
        values = twoArrayHashMap.values;
        M = twoArrayHashMap.M;
    }
}


