package algorithms4.Graph;

import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * Created by Ryan on 2017/4/24.
 */
public class UndirectedGraph {
    /**
     * 无向图中的内部实现-链表
     * @param <Item>
     */
    class LinkedList<Item> implements Iterable<Item> {
        Node first;
        class Node {
            Item item;
            Node next;

            public Node(Item item, Node next) {
                this.item = item;
                this.next = next;
            }

            public Node() {

            }
        }

        public void add(Item item) {
            first = new Node(item,first);
        }

        public Iterator<Item> iterator() {
            return new Iterator<Item>() {
                private Node current = first;
                @Override
                public boolean hasNext() {
                    if (current.next != null)
                        return true;
                    return false;
                }

                @Override
                public Item next() {
                    Item item = current.item;
                    current = current.next;
                    return item;
                }
            };
        }
    }

    /**
     * 图的内部数据结构
     */

    private final int V; //顶点数目
    private int E; //边的数目
    private LinkedList<Integer>[] adj; //邻接表 - 每个顶点所连接的其他的顶点

    public UndirectedGraph(int v) {
        V = v;
        E = 0;
        adj = new LinkedList[V];
        for (int i = 0; i < v ; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public UndirectedGraph(In in) {
        this(in.readInt());
        int E = in.readInt(); //读取边数-该E只是边数的数量，和field中的E的结果相等 - 图完成之后
        for (int i = 0; i < E ; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public static int degree(UndirectedGraph G, int v) {
        int degree = 0;
        for (int w:G.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(UndirectedGraph G) {
        int max = 0;
        int N = G.adj.length;
        for (int i = 0; i < N; i++) {
            if (max < degree(G,i))
                max = degree(G,i);
        }
        return max;
    }

    public static double aveDegree(UndirectedGraph G) {
        return 2 * G.E()/G.V();
    }

    public static int numberOfSelfLoops(UndirectedGraph G) {
        int count = 0;
        for (int i = 0; i < G.V() ; i++) {
            for (int w:G.adj(i)) {
                if (w == i)
                    count++;
            }
        }
        return count/2; //因为addEdge时加了两次
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        StringBuilder a = new StringBuilder(s);
        for (int i = 0; i < V ; i++) {
            a.append(i + ": ");
            for (int w: this.adj(i))
                a.append(w + " ");
            a.append("\n");
        }
        return a.toString();
    }
}
