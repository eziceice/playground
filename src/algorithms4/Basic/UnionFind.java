package algorithms4.Basic;

/**
 * Created by Ryan on 2017/4/5.
 */

/**
 * Complexity O(N^2)
 */
public class UnionFind {
    private int[] id;
    private int count;

    public UnionFind(int size) {
        id = new int[size];
        for (int i = 0; i < size ; i++) {
            id[i] = i;
        }
        count = size;
    }

    public void union(int p, int q) {
        if (connected(p,q))
            return;
        for (int i = 0; i < id.length ; i++) {
            if (id[i] == id[q])
                id[i] = id[p];
        }
        count--;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public int count() {
        return count;
    }
}

/**
 * Complexity O(N^2)
 */
class QuickUnion {
    private int[] id;
    private int count;

    public QuickUnion(int size) {
        id = new int[size];
        for (int i = 0; i < size ; i++) {
            id[i] = i;
        }
        count = size;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        id[pRoot] = qRoot;
        count--;
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}

/**
 * Complexity O(lgN)
 */
class WeightedQuickUnion {
    private int[] id;
    private int count;
    private int[] sz;

    public WeightedQuickUnion(int size) {
        id = new int[size];
        for (int i = 0; i < size ; i++) {
            id[i] = i;
        }
        sz = new int[size];
        for (int i = 0; i < size ; i++) {
            sz[i] = 1;
        }
        count = size;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
