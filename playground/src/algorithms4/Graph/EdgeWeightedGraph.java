package algorithms4.Graph;

import java.util.LinkedList;

/**
 * Created by Ryan on 2017/4/27.
 */
public class EdgeWeightedGraph {
    private int V;
    private LinkedList<Edge>[] edges;
    private int E;

    public EdgeWeightedGraph(int v) {
        V = v;
        edges = new LinkedList[v];
        E = 0;
        for (int i = 0; i < edges.length ; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Edge> adj(int v) {
        return edges[v];
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> edgeAll = new LinkedList<>();
        for (int i = 0; i < V ; i++) {
            for (Edge e:adj(i)) {
                if (e.other(i) > i) //避免加入重复的边，因此只加入未加入过的边
                    edgeAll.add(e);
            }
        }
        return edgeAll;
    }

    public void addEdge(Edge e) {
        int s = e.either();
        int w = e.other(s);
        edges[s].add(e);
        edges[w].add(e);
        E++;
    }
}

class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }


    /**
     * 当已知一个顶点是，可以用other()方法。
     * 当两个顶点都不知道时，用 v = either()获取一个顶点，然后用 w = other(v)获取另外一个顶点
     * @return
     */
    public int either() {
        return v;
    }

    public int other(int v) {
        if (v == this.v)
            return w;
        else if (v == this.w)
            return v;
        else
            throw new RuntimeException();
    }


    @Override
    public int compareTo(Edge o) {
        if (o.weight > weight)
            return -1;
        else if (o.weight < weight)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
