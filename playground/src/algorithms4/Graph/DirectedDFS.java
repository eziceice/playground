package algorithms4.Graph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by Ryan on 2017/4/26.
 */
public class DirectedDFS {
    private boolean[] marked;
    private Queue<Integer> preQueue; //所有顶点的前序排列
    private Queue<Integer> postQueue; //所有顶点的后序排列
    private Stack<Integer> reversePost; //所有顶点的后序排列的逆序列 - 拓扑序列(保证无环的情况下)

    public DirectedDFS(DirectedGraph G) {
        preQueue = new Queue<>();
        postQueue = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V() ; i++) {
            if (!marked[i])
                dfs(G,i);
        }
    }

    public DirectedDFS(DirectedGraph G, int s) {
        marked = new boolean[G.V()];
        for (int w:G.adj(s)) {
            if (!marked[w])
                dfs(G,w);
        }
    }

    private void dfs(DirectedGraph G, int s) {
        marked[s] = true;
        preQueue.enqueue(s);
        for (int w:G.adj(s)) {
            if (!marked[w])
                dfs(G,w);
        }
        postQueue.enqueue(s);
        reversePost.push(s);
    }

    public boolean marked(int s) {
        return marked[s];
    }

    public Iterable<Integer> pre() {
        return preQueue;
    }

    public Iterable<Integer> post() {
        return postQueue;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
