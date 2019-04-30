package algorithms4.Graph;

import Basic.UnionFind;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Ryan on 2017/4/28.
 */
public class KrusukalMST {
    private Queue<Edge> mst;

    public KrusukalMST(EdgeWeightedGraph G) {
        mst = new LinkedList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        UnionFind uf = new UnionFind(G.V());
        for (Edge e: G.edges()) {
            pq.add(e);
        }

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v,w))
                continue;
            uf.union(v,w);
            mst.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        Double weight = 0.0;
        for (Edge e: mst) {
            weight += e.weight();
        }
        return weight;
    }
}
