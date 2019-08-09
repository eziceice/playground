package algorithms4.Search;

import java.util.LinkedList;

/**
 * Created by Ryan on 2017/4/13.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left,right;
        private int N; //以该结点为根的子树中的结点总数 - 包含root本身

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.N;
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

    public void put(Key key, Value value) {
        root = put(root,key,value);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null)
            return new Node(key,val,1);
        int cmp = key.compareTo(node.key);
        if (cmp > 0)
           node.right = put(node.right,key,val);
        else if (cmp < 0)
           node.left = put(node.left,key,val);
        else
            node.value = val;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key min() {
        return min(root).key;
    }


    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node node) {
        if (node.right == null)
            return node.key;
        return max(node.right);
    }

    public Key floor(Key key) {
        return floor(key,root).key;
    }

    private Node floor(Key key, Node node) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return floor(key,node.left);
        if (cmp == 0)
            return node;
        Node t = floor(key,node.right);
        if (t == null)
            return node;
        else
            return t;
    }

    public Key ceiling(Key key) {
        return ceiling(key,root).key;
    }

    private Node ceiling(Key key, Node node) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0)
            return ceiling(key,node.right);
        if (cmp == 0)
            return node;
        Node t = ceiling(key, node.left);
        if (t == null)
            return node;
        else
            return t;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node,int k) {
        if (node == null)
            return null;
        int size = size(node.left);
        if (size > k)
            return select(node.left,k);
        else if (size == k)
            return node;
        else
            return select(node.right,k-size-1);
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node node) {
        if (node == null)
            return 0;
        int cmp = key.compareTo(node.key);
        if (cmp > 0)
            return 1 + size(node.left) + rank(key,node.right);
        else if (cmp == 0)
            return size(node.left);
        else
            return rank(key,node.left);
        
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        root = delete(key,root);
    }

    private Node delete(Key key, Node node) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0)
            node.right = delete(key,node.right);
        else if (cmp < 0)
            node.left = delete(key,node.left);
        else {
            if (node.right == null)
                return node.left;
            if (node.left == null)
                return node.right;
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(node.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private void print(Node x) {
        if (x == null)
            return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    public Iterable<Key> keys() {
        return keys(min(),max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> queue = new LinkedList<>();
        keys(root, queue, lo ,hi);
        return queue;
    }

    private void keys(Node x, LinkedList<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left,queue,lo,hi);
        if (cmplo >= 0 && cmphi <= 0)
            queue.add(x.key);
        if (cmphi > 0)
            keys(x.right,queue,lo,hi);
    }
}
