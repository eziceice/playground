package algorithms4.Graph;

import java.util.LinkedList;

/**
 * Created by Ryan on 2017/4/26.
 */
public class DirectedGraph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public DirectedGraph(int v) {
        V = v;
        E = 0;
        adj = new LinkedList[V];
        for (int i = 0; i < V ; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int w, int v) {
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int w) {
        return adj[w];
    }

    public DirectedGraph reverse() {
        DirectedGraph directedGraph = new DirectedGraph(V);
        for (int i = 0; i < V; i++) {
            for (int w: adj(i))
                directedGraph.addEdge(w,i);
        }
        return directedGraph;
    }
}
