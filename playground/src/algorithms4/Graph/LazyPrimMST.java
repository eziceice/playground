package algorithms4.Graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Ryan on 2017/4/28.
 */
public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> queue;
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        queue = new LinkedList<>();
        pq = new PriorityQueue<>();

        visit(G,0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w])
                continue;
            queue.offer(e);
            if (!marked[v])
                visit(G,v);
            if (!marked[w])
                visit(G,w);
        }
    }


    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) {
            if (!marked[e.other(v)])
                pq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return queue;
    }

    public double weight() {
        double allWeight = 0d;
        for (Edge e: queue)
            allWeight += e.weight();
        return allWeight;
    }
}
