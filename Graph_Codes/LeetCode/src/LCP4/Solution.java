package LCP4;

import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

public class Solution {

    public class Graph implements Cloneable{

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    private boolean directed;
    private int[] indegrees, outdegrees;

    public Graph(int V, boolean directed){
        this.V = V;
        this.directed = directed;
        this.E = 0;
        adj = new TreeSet[V];
        for(int i=0; i<V; i++)
            adj[i] = new TreeSet<>();
    }

    public void addEdge(int a, int b){
        validateVertex(a);
        validateVertex(b);
        if(a == b) throw new IllegalArgumentException("Self loop is detected.");
        if(adj[a].contains(b))
            throw new IllegalArgumentException("Parallel Edges are detected.");
        adj[a].add(b);
        if(!directed)
            adj[b].add(a);
        this.E ++;
    }

    public boolean isDirected(){
        return directed;
    }

    public void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid.");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }
}

    public class WeightedGraph implements Cloneable{

    private int V;
    private int E;
    // 第一个Integer指的是与v相邻的顶点w，第二个Integer指的是v与w之间的权重
    private TreeMap<Integer, Integer>[] adj;
    private boolean directed;

    public WeightedGraph(int V, boolean directed){
        this.V = V;
        this.directed = directed;
        this.E = 0;

        adj = new TreeMap[V];
        for(int i=0; i<V; i++)
            adj[i] = new TreeMap<>();
    }

    public void addEdge(int a, int b, int weight){
        validateVertex(a);
        validateVertex(b);
        if(a == b) throw new IllegalArgumentException("Self loop is detected.");
        if(adj[a].containsKey(b))
            throw new IllegalArgumentException("Parallel Edges are detected.");
        adj[a].put(b, weight);
        if(!directed)
            adj[b].put(a, weight);
        this.E ++;
    }

    public boolean isDirected(){
        return directed;
    }

    public void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid.");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].containsKey(w);
    }

    // 这里只返回与顶点v相邻的顶点，而不返回它们的权值
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v].keySet();
    }

    public int getWeight(int v, int w){
        if(hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    public void setWeight(int v, int w, int newWeight){
        if(!hasEdge(v, w))
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
        adj[v].put(w, newWeight);
        if(!directed)
            adj[w].put(v, newWeight);
    }
}

    public class BipartitionDetection {

        private Graph G;
        private boolean[] visited;
        private int[] colors;
        private boolean isBipartite = true;

        public BipartitionDetection(Graph G){
            this.G = G;
            visited = new boolean[G.V()];
            colors = new int[G.V()];
            for(int i=0; i<G.V(); i++)
                colors[i] = -1;
            for(int v = 0; v < G.V(); v++)
                if(!visited[v])
                    if(!dfs(v, 0)){
                        isBipartite = false;
                        break;
                    }
        }

        private boolean dfs(int v, int color){
            visited[v] = true;
            colors[v] = color;
            for(int w: G.adj(v))
                if(!visited[w]){
                    if(!dfs(w, 1-color)) return false;
                }
                else if(colors[w] == colors[v])
                    return false;
            return true;
        }

        public boolean isBipartite() {
            return isBipartite;
        }

        public int[] colors(){
            return colors;
        }
    }

    public class MaxFlow {

        private WeightedGraph network;
        private int s, t;
        private WeightedGraph rG; // residual Graph
        private int maxFlow;

        public MaxFlow(WeightedGraph network, int s, int t){

            if(!network.isDirected())
                throw new IllegalArgumentException("MaxFlow only works in directed graph.");
            if(network.V() < 2)
                throw new IllegalArgumentException("Network should have at least 2 vertices.");
            network.validateVertex(s);
            network.validateVertex(t);
            if(s == t)
                throw new IllegalArgumentException("s and t should be different.");
            this.network = network;
            this.s = s;
            this.t = t;

            // 构造残量图
            this.rG = new WeightedGraph(network.V(), true);
            for (int v = 0; v < network.V(); v++) {
                for(int w: network.adj(v)){
                    int c = network.getWeight(v, w);
                    rG.addEdge(v, w, c);
                    rG.addEdge(w, v, 0);
                }
            }

            // 不断在残量图中找増广路径
            while(true){
                ArrayList<Integer> augPath = getAugmentingPath();
                if(augPath.size() == 0) break;

                // 计算増广路径上的最小值
                int f = Integer.MAX_VALUE;
                for (int i = 1; i < augPath.size(); i++) {
                    int v = augPath.get(i - 1);
                    int w = augPath.get(i);
                    f = Math.min(f, rG.getWeight(v, w));
                }
                maxFlow += f;

                // 根据増广路径更新 rG
                for (int i = 1; i < augPath.size(); i++) {
                    int v = augPath.get(i - 1);
                    int w = augPath.get(i);

                    // 不管v,w是正向边还是反向边，操作逻辑是一样的
                    rG.setWeight(v, w, rG.getWeight(v, w) - f);
                    rG.setWeight(w, v, rG.getWeight(w, v) + f);
                }
            }
        }

        private ArrayList<Integer> getAugmentingPath(){
            Queue<Integer> q = new LinkedList<>();
            int[] pre = new int[network.V()];
            Arrays.fill(pre, -1);

            q.add(s);
            pre[s] = s;
            while(!q.isEmpty()){
                int cur = q.remove();
                if(cur == t) break;
                for(int next: rG.adj(cur))
                    if(pre[next] == -1 && rG.getWeight(cur, next) > 0){
                        pre[next] = cur;
                        q.add(next);
                    }
            }

            ArrayList<Integer> res = new ArrayList<>();
            if(pre[t] == -1) return res;
            int cur = t;
            while(cur != s){
                res.add(cur);
                cur = pre[cur];
            }
            res.add(s);
            Collections.reverse(res);
            return res;
        }

        public int result(){
            return maxFlow;
        }
    }

    public class BipartiteMatching {
        private Graph G;
        private int maxMatching;

        public BipartiteMatching(Graph G){
            // 传来的G是无向图，判断是否是二分图后，构造有向图用最大流算法求解
            BipartitionDetection bd = new BipartitionDetection(G);
            if(!bd.isBipartite())
                throw new IllegalArgumentException(
                        "BipartiteMatching only works in bipartite graph.");
            this.G = G;

            int[] colors = bd.colors();
            // 源点为V，汇点为V+1
            WeightedGraph network = new WeightedGraph(G.V() + 2, true);
            for(int v=0; v<G.V(); v++){
                if(colors[v] == 0) network.addEdge(G.V(), v, 1);
                else network.addEdge(v, G.V() + 1, 1);

                for(int w: G.adj(v))
                    if(v < w){
                        if(colors[v] == 0) network.addEdge(v, w, 1);
                        else network.addEdge(w, v, 1);
                    }
            }
            MaxFlow maxFlow = new MaxFlow(network, G.V(), G.V() + 1);
            maxMatching = maxFlow.result();
        }

        public int maxMatching(){
            return maxMatching;
        }
    }

    public int domino(int n, int m, int[][] broken) {

        Graph g = new Graph(n * m, false);

        int[][] board = new int[n][m];
        for(int[] p: broken)
            board[p[0]][p[1]] = 1;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++){
                if(j + 1 < m && board[i][j] == 0 && board[i][j+1] == 0)
                    g.addEdge(i * m + j, i * m + j + 1);
                if(i + 1 < n && board[i][j] == 0 && board[i+1][j] == 0)
                    g.addEdge(i * m + j, (i + 1) * m + j);
            }

        BipartiteMatching bm = new BipartiteMatching(g);
        return bm.maxMatching();
    }
}
