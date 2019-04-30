package algorithms4.Graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;

/**
 * Created by Ryan on 2017/4/25.
 */
public class SymbolGraph {
    private HashMap<String,Integer> st; //符号名 -> 索引
    private String[] keys; // 索引 -> 符号名 反向索引
    private Graph G;

    public SymbolGraph(String stream, String sp) {
        st = new HashMap<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(sp);
            for (int i = 0; i < s.length ; i++) {
                if (!st.containsKey(s[i]))
                    st.put(s[i],st.size());
            }
        }

        for (String name:st.keySet()) {
            keys[st.get(name)] = name;
        }

        /**
         * 构图 - 链接有关的edge和顶点
         */
        G = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(sp);
            int v = st.get(s[0]);
            for (int i = 0; i < s.length; i++) {
                G.addEdge(v,st.get(s[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.containsKey(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int i) {
        return keys[i];
    }

    public Graph G() {
        return G;
    }
}
