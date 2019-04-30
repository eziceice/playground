package algorithms4.Graph;

import edu.princeton.cs.algs4.Graph;

/**
 * Created by Ryan on 2017/4/24.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        count++;
        for (int w:G.adj(s)) {
            if (!marked[w])
                dfs(G,w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
