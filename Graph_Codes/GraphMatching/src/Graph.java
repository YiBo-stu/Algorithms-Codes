import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

// 无权图（支持有向、无向）
public class Graph implements Cloneable{

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    private boolean directed;
    private int[] indegrees, outdegrees;

    public Graph(String filename, boolean directed){
        this.directed = directed;
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative.");

            adj = new TreeSet[V];
            for(int i=0; i<V; i++)
                adj[i] = new TreeSet<>();
            indegrees = new int[V];
            outdegrees = new int[V];

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative.");
            for(int i=0; i<E; i++){
                int a = scanner.nextInt(); validateVertex(a);
                int b = scanner.nextInt(); validateVertex(b);
                if(a == b) throw new IllegalArgumentException("Self loop is detected.");
                if(adj[a].contains(b))
                    throw new IllegalArgumentException("Parallel Edges are detected.");
                adj[a].add(b);
                if(directed){
                    outdegrees[a] ++; indegrees[b] ++;
                }
                else adj[b].add(a);
            }
        }
        catch (IOException e){ e.printStackTrace(); }
    }

    public Graph(TreeSet<Integer>[] adj, boolean directed){
        this.adj = adj;
        this.directed = directed;
        this.V = adj.length;
        this.E = 0;
        indegrees = new int[V];
        outdegrees = new int[V];
        for(int v=0; v<V; v++)
            for(int w: adj[v]){
                outdegrees[v] ++;
                indegrees[w] ++;
                this.E ++;
            }
        if(!directed) this.E /= 2;
    }

    // 为了求解强连通分量而设计的类
    public Graph reverseGraph(){
        TreeSet<Integer>[] rAdj = new TreeSet[V];
        for(int i=0; i<V; i++)
            rAdj[i] = new TreeSet<>();
        for(int v=0; v<V; v++)
            for(int w: adj(v))
                rAdj[w].add(v);
        return new Graph(rAdj, directed);
    }

    public Graph(String filename){
        this(filename, false);
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

    public int degree(int v){
        if(directed)
            throw new RuntimeException("degree only works in undirected graph.");
        validateVertex(v);
        return adj[v].size();
    }

    public int indegree(int v){
        if(!directed)
            throw new RuntimeException("indegree only works in directed graph.");
        validateVertex(v);
        return indegrees[v];
    }

    public int outdegree(int v){
        if(!directed)
            throw new RuntimeException("outdegree only works in directed graph.");
        validateVertex(v);
        return outdegrees[v];
    }

    public void removeEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        if(adj[v].contains(w)){
            E --;
            if(directed){
                outdegrees[v] --;
                indegrees[w] --;
            }
        }
        adj[v].remove(w);
        if(!directed)
            adj[w].remove(v);
    }

    @Override
    public Object clone(){
        try{
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[V];
            for(int v=0; v<V; v++){
                cloned.adj[v] = new TreeSet<>();
                for(int w: adj[v])
                    cloned.adj[v].add(w);
            }
            return cloned;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
        for(int v=0; v<V; v++){
            res.append(String.format("%d : ", v));
            for(int w: adj[v])
                res.append(String.format("%d ", w));
            res.append('\n');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        System.out.println(g);

        for (int v = 0; v < g.V(); v++) {
            System.out.println(g.indegree(v) + " " + g.outdegree(v));
        }
    }
}
