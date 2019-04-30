package algorithms4.Graph;

import edu.princeton.cs.algs4.Graph;

/**
 * Created by Ryan on 2017/4/25.
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G){
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            if (!marked[i])
                dfs(G,i,i);
        }
    }

    private void dfs(Graph G, int v, int c) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w])
                dfs(G,w,v);
            else if (w != c)
                hasCycle = true;
        }
    }
}

class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            if (!marked[i])
                dfs(G,i);
        }
    }

    private void dfs(Graph G,int s) {
        marked[s] = true;
        for (int w:G.adj(s)) {
            if (!marked[w]) {
                color[w] = !color[s];
                dfs(G,w);
            }
            else if (color[w] == color[s])
                isTwoColor = false;
        }
    }
}

