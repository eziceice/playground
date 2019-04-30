package algorithms4.Graph;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Ryan on 2017/5/1.
 */
public class EdgeWeightedDirectedGraph{
    private final int V;
    private int E;
    private LinkedList<DirectedEdge>[] adj;

    public EdgeWeightedDirectedGraph(int v) {
        V = v;
        E = 0;
        adj = new LinkedList[v];
        for (int i = 0; i < adj.length ; i++)
            adj[i] = new LinkedList<>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge directedEdge) {
        int from = directedEdge.from();
        adj[from].add(directedEdge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> edges = new ArrayList<>();
        for (int i = 0; i < V ; i++) {
            for (DirectedEdge e:adj(i))
                edges.add(e);
        }
        return edges;
    }
}

class DirectedEdge {
    private int v; // 起点
    private int w; // 终点
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f",v,w,weight);
    }
}

class SPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private int s;  //起点
    public SPath(EdgeWeightedDirectedGraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        this.s = s;
        for (int i = 0; i < G.V() ; i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;


    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > e.weight() + distTo[v]) {
            distTo[w] = e.weight() + distTo[v];
            edgeTo[w] = e;
        }
    }

    private void relax(EdgeWeightedDirectedGraph G, int v) {
        for (DirectedEdge w:G.adj(v)) {
            int s = w.to();
            if (distTo[s] > distTo[v] + w.weight()) {
                distTo[s] = distTo[v] + w.weight();
                edgeTo[s] = w;
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int i) {
        if (hasPathTo(i)) {
            LinkedList<DirectedEdge> path = new LinkedList<>();
            for (DirectedEdge de = edgeTo[i]; de != null ; de = edgeTo[de.from()])
                path.push(de);
            return path;
        }
        return null;
    }

}

class DijkstraSP{
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    //private PriorityQueue<Item> priorityQueue;

    private boolean[] marked;

    public DijkstraSP(EdgeWeightedDirectedGraph G,int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());

        //priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < distTo.length ; i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        /**
        Item item = new Item(0,0.0);
        priorityQueue.add(item);
        while (!priorityQueue.isEmpty()) {
            marked[priorityQueue.peek().index] = true;
            relax(G,priorityQueue.poll().index);
        }
         */

        pq.insert(0,0.0);
        while (!pq.isEmpty()) {
            marked[pq.minIndex()] = true; //弹出的值都为找到最短并且mark它
            relax(G, pq.delMin());
        }
    }

    /**
     * 这个方法可以允许有负值权重，但是性能无法估计，有时会很差
     * @param G
     * @param s
     */

    private void relax(EdgeWeightedDirectedGraph G, int s) {
        for (DirectedEdge e: G.adj(s)) {
            int w = e.to();
            if (distTo[w] > distTo[s] + e.weight()) {
                distTo[w] = distTo[s] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w))
                    pq.changeKey(w,distTo[w]);
                else
                    pq.insert(w,distTo[w]);
            }
        }
        /**
         *
         * 找到最小值之后将该点设置为已访问过
        for (DirectedEdge e: G.adj(s)) {
            int w = e.to();
            if (marked[w])
                continue;
            else {
                if (distTo[w] > distTo[s] + e.weight()) {
                    distTo[w] = distTo[s] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w))
                        pq.changeKey(w, distTo[w]);
                    else
                        pq.insert(w, distTo[w]);
                }
            }
        }
         */



    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int i) {
        if (hasPathTo(i)) {
            LinkedList<DirectedEdge> path = new LinkedList<>();
            for (DirectedEdge de = edgeTo[i]; de != null ; de = edgeTo[de.from()])
                path.push(de);
            return path;
        }
        return null;
    }

    class Item implements Comparable<Item>{
        int index;
        double key;

        public Item(int index, double key) {
            this.index = index;
            this.key = key;
        }

        @Override
        public int compareTo(Item o) {
            if (o.key > key)
                return -1;
            else if (o.key < key)
                return 1;
            else
                return 0;
        }
    }
}

class DijkstraAllPairsSP {
    DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDirectedGraph G) {
        all = new DijkstraSP[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            all[i] = new DijkstraSP(G,i);
        }
    }

    public Iterable<DirectedEdge> path(int s, int w) {
        return all[s].pathTo(w);
    }

    public double distTo(int s, int w) {
        return all[s].distTo(w);
    }

    public static void main(String[] args) {
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(5);
        pq.insert(0,0);
        pq.insert(1,1);
        pq.insert(2,2);
        pq.insert(3,3);
        pq.insert(4,4);
        pq.delMin();
        pq.insert(0,0);
        for (int s:pq) {
            System.out.println(s);
        }
    }
}

class BellmanFordSP {
    private double[] distTo;              //从起点到某个顶点的路径
    private DirectedEdge[] edgeTo;        //从起点到某个顶点的最后一条边
    private boolean[] onQ;                //该顶点是否存在于队列中
    private Queue<Integer> queue;         //正在被放松的顶点
    private int cost;                     //relax()的调用次数
    private Iterable<DirectedEdge> cycle; //edgeTo[]中是否含有负权重环

    public BellmanFordSP(EdgeWeightedDirectedGraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedList<>();
        for (int i = 0; i < G.V(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        queue.offer(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.poll();
            onQ[v] = false;
            relax(G,v);
        }
    }

    private void relax(EdgeWeightedDirectedGraph G, int s) {
        for (DirectedEdge d:G.adj(s)) {
            int w = d.to();
            if (distTo[w] > distTo[s] + d.weight()) {
                distTo[w] = distTo[s] + d.weight();
                edgeTo[w] = d;
                if (!onQ[w]) {
                    queue.offer(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        LinkedList<DirectedEdge> result = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            result.push(e);
        return result;
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }


    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDirectedGraph spt;
        spt = new EdgeWeightedDirectedGraph(V);
        for (int i = 0; i < V ; i++) {
            if (edgeTo[i] != null)
                spt.addEdge(edgeTo[i]);
        }
        DirectedCycleFinder finder = new DirectedCycleFinder(spt);
        cycle = finder.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
