package algorithms4.Search;

/**
 * Created by Ryan on 2017/4/20.
 */
public class HashMapSearch {
    public static void main(String[] args) {

    }

}


class SeparateChainingHashST<Key, Value> {
    private int N; // 键值对总数
    private int M; // 散列表大小
    private LinkedListMap<Key,Value>[] st; // 存放链表对象的数组

    public SeparateChainingHashST(int m) {
        M = m;
        st = new LinkedListMap[m];
        for (int i = 0; i < m ; i++) {
            st[i] = new LinkedListMap<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key,Value value) {
        st[hash(key)].put(key,value);
    }

    public boolean delete(Key key) {
        return st[hash(key)].delete(key);
    }
}

class LinkedListMap<Key,Value> {
    Node first;
    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Key key,Value value) {
        for (Node x = first; x.next != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
            first = new Node(key,value,first);
        }
    }

    public Value get(Key key) {
        for (Node x = first; x.next != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
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

    public int size() {
        int N = 0;
        for (Node x = first; x.next != null; x = x.next) {
            N++;
        }
        return N;
    }
}



class LinerProbingHashSt<Key,Value> {
    private int N; //键值对数量
    private int M; //线性探测表总数
    private Key[] keys;
    private Value[] values;

    public LinerProbingHashSt(int m) {
        M = m;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
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

    public boolean delete(Key key) {
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
            Value reputVal = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(reputKey,reputVal);
            i = (i + 1) % M;
        }
        if (N > 0 && N == M/8)
            resize(M/2);
        return true;
    }

    public boolean contains(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key))
                return true;
        }
        return false;
    }

    private void resize(int cap) {
        LinerProbingHashSt<Key,Value> t = new LinerProbingHashSt<>(cap);
        for (int i = 0; i < M ; i++) {
            if (keys[i] != null)
                put(keys[i], values[i]);
        }
        keys = t.keys;
        values = t.values;
        M = cap;
    }
}