import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HungarianDFS {

    private Graph G;
    private int maxMatching = 0;
    private int[] matching;
    private boolean[] visited;

    public HungarianDFS(Graph G){

        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartite())
            throw new IllegalArgumentException(
                    "HungarianBFS only works in bipartite graph.");
        this.G = G;
        int[] colors = bd.colors();
        matching = new int[G.V()];
        Arrays.fill(matching, -1);
        visited = new boolean[G.V()];
        for(int v=0; v<G.V(); v++)
            if(colors[v] == 0 && matching[v] == -1) {
                // 每次开始遍历前都要把visited设置为false
                Arrays.fill(visited, false);
                if (dfs(v)) maxMatching++;
            }
    }

    private boolean dfs(int v){
        // 需要使用数组标记哪些顶点已经被访问过，BFS非递归可以在方法内声明pre数组
        // 而DFS是递归函数，需要在方法外面声明数组visited
        visited[v] = true;
        for(int u: G.adj(v))
            if(!visited[u]){
                // 右半部分的点不会当做参数调用dfs
                visited[u] = true;
                if(matching[u] == -1 || dfs(matching[u])){
                    matching[v] = u;
                    matching[u] = v;
                    return true;
                }
                /*
                else if(dfs(matching[u])){
                    matching[v] = u;
                    matching[u] = v;
                    return true;
                }
                */
            }
        return false;
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        HungarianDFS hungarianDFS = new HungarianDFS(g);
        System.out.println(hungarianDFS.maxMatching());
        System.out.println(hungarianDFS.isPerfectMatching());

        Graph g2 = new Graph("g2.txt");
        HungarianDFS hungarianDFS2 = new HungarianDFS(g2);
        System.out.println(hungarianDFS2.maxMatching());
        System.out.println(hungarianDFS2.isPerfectMatching());
    }
}

