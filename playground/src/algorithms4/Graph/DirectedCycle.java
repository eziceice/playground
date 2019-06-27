package algorithms4.Graph;

import java.util.LinkedList;

/**
 * Created by Ryan on 2017/4/26.
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private LinkedList<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(DirectedGraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i])
                dfs(G,i);
        }
    }

    private void dfs(DirectedGraph G, int s) {
        onStack[s] = true;
        marked[s] = true;
        for (int w:G.adj(s)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = s;
                dfs(G,w);
            }
            else if (onStack[w]) {
                cycle = new LinkedList<>();
                for (int x = s; x!= w; x = edgeTo[x])
                    cycle.push(x);

            }
        }
        onStack[s] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
