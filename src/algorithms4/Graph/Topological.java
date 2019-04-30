package algorithms4.Graph;

/**
 * Created by Ryan on 2017/4/26.
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(DirectedGraph G) {
        DirectedCycle dc = new DirectedCycle(G);
        if (!dc.hasCycle()) {
            DirectedDFS directedDFS = new DirectedDFS(G);
            order = directedDFS.reversePost();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
