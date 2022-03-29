import java.io.File;
import java.util.ArrayList;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    boolean hasCycle = false;

    public CycleDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
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
        for(int w: G.adj(v))
            if(!visited[w]){
                if(dfs(w, v)) return true;
            }
            else if(w != parent)
                return true;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
