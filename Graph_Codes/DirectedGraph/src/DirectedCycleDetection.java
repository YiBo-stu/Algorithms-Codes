import java.io.File;
import java.util.ArrayList;

public class DirectedCycleDetection {

    private Graph G;
    private boolean[] visited;
    // 新的标记用于记录是否在当前路径上
    private boolean[] onPath;
    boolean hasCycle = false;

    public DirectedCycleDetection(Graph G){
        if(!G.isDirected())
            throw new IllegalArgumentException("CycleDetection only works in directed graph.");
        this.G = G;
        visited = new boolean[G.V()];
        onPath = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++)
            if(!visited[v])
                if(dfs(v, v)){
                    hasCycle = true;
                    break;
                }
    }

    // 从顶点v开始判断图中是否有环
    private boolean dfs(int v, int parent){
        visited[v] = true;
        onPath[v] = true;
        for(int w: G.adj(v))
            if(!visited[w]){
                if(dfs(w, v)) return true;
            }
            // w != parent这个条件不再需要
            else if(onPath[w])
                return true;
        onPath[v] = false;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        DirectedCycleDetection cycleDetection = new DirectedCycleDetection(g);
        System.out.println(cycleDetection.hasCycle());
    }
}
