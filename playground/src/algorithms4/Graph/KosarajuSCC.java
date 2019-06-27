package algorithms4.Graph;

/**
 * Created by Ryan on 2017/4/27.
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(DirectedGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        /**
         * 1.在给定的一幅有向图G中，使用DepthFirstOrder来计算该有向图G中的reverseOder G.（反向图）
         * 2.对该反向图的拓扑序列进行深度优先遍历 和 无向图深度优先遍历求connected一样的算法.
         */
        DirectedDFS dfs = new DirectedDFS(G.reverse());
        for (int w: dfs.reversePost()) {
            if (!marked[w]) {
                dfs(G, w);
                count++;
            }
        }
    }

    private void dfs(DirectedGraph G, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w: G.adj(s)) {
            if (!marked[w])
                dfs(G,w);
        }
    }

    public boolean stronglyConnected(int w, int s) {
        return id[w] == id[s];
    }

    public int count() {
        return count;
    }

    public int id(int w) {
        return id[w];
    }
}
