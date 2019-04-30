package algorithms4.Graph;

/**
 * Created by Ryan on 2017/4/27.
 */
public class TransitivieClosure {
    private DirectedDFS[] st;

    public TransitivieClosure(DirectedGraph G) {
        st = new DirectedDFS[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            st[i] = new DirectedDFS(G,i);
        }
    }

    public boolean reachable(int w, int v) {
        return st[w].marked(v);
    }
}
