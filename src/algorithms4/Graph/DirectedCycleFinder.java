package algorithms4.Graph;

import java.util.LinkedList;

/**
 * Created by Ryan on 2017/5/3.
 */
public class DirectedCycleFinder {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private LinkedList<DirectedEdge> cycle;

    public DirectedCycleFinder(EdgeWeightedDirectedGraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            if (!marked[i])
                dfs(G,i);
        }
    }

    private void dfs(EdgeWeightedDirectedGraph G, int s) {
        onStack[s] = true;
        marked[s] = true;
        for (DirectedEdge e:G.adj(s)) {
            int w = e.to();
            if (hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G,w);
            }
            else if (onStack[w]) {
                cycle = new LinkedList<>();
                DirectedEdge edge;
                for (edge = e; edge.from() != w; edge = edgeTo[edge.from()])
                    cycle.push(edge);
                cycle.push(edge);

            }
        }
        onStack[s] = false;
    }

    private boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
