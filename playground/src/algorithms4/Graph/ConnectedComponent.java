package algorithms4.Graph;

import edu.princeton.cs.algs4.Graph;

/**
 * Created by Ryan on 2017/4/25.
 */
public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            if (!marked[i]) {
                dfs(G,i);
                count++;
            }
        }
    }

    private void dfs(Graph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w:G.adj(s)) {
            if (!marked[w])
                dfs(G,w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int w) {
        return id[w];
    }

    public int count() {
        return count;
    }
}
