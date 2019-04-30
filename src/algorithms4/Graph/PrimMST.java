package algorithms4.Graph;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2017/4/28.
 */
public class PrimMST {
    private Edge[] edgeTo; //距离树最近的边
    private double[] distTo; // distTo[w] = edgeTo[w].weight();
    private boolean[] marked; // 如果v在树中为true
    private IndexMinPQ<Double> pq; //有效的横切边 索引优先的优先队列

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(G.V());
        distTo[0] = 0.0;
        pq.insert(0,0.0);
        while (!pq.isEmpty())
            visit(G,pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            int w = e.other(v);
            if (marked[w])
                continue;
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w))
                    pq.changeKey(w,distTo[w]);
                else
                    pq.insert(w,distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < edgeTo.length ; i++) {
            list.add(edgeTo[i]);
        }
        return list;
    }

    public double weight() {
       Iterable<Edge> list = edges();
       double weight = 0;
        for (Edge e: list) {
            weight += e.weight();
        }
        return weight;
    }
}
