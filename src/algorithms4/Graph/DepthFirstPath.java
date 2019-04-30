package algorithms4.Graph;

import edu.princeton.cs.algs4.Graph;

import java.util.LinkedList;

/**
 * Created by Ryan on 2017/4/24.
 */
public class DepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int s; // start point

    public DepthFirstPath(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G,s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        for (int w: G.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s;
                dfs(G,w);
            }
        }
    }

    private boolean hasPathTo(int v) {
        return marked[v];
    }

    private Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = v; i != s; i = edgeTo[v])
            linkedList.push(i);
        linkedList.push(s);
        return linkedList;
    }
}
