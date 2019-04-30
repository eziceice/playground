package algorithms4.Graph;

import edu.princeton.cs.algs4.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Ryan on 2017/4/25.
 */
public class BreadthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPath(Graph G,int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w:G.adj(v)) {
                if (!marked[w]) {
                    queue.offer(w);
                    edgeTo[w] = v;
                    marked[w] = true;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;
        LinkedList<Integer> path = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
