package Q785;

import java.util.Arrays;

// pratice again
public class Solution2 {

    private int[][] graph;
    private boolean[] visited;
    private int[] colors;
    private int V;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        V = graph.length;
        visited = new boolean[V];
        colors = new int[V];
        Arrays.fill(colors, -1);
        for(int v=0; v<V; v++)
            if(!visited[v])
                if(!dfs(v, 0))
                    return false;
        return true;
    }

    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;
        for(int w: graph[v])
            if(!visited[w]){
                if(!dfs(w, 1 - color))
                    return false;
            }
            else if(colors[w] == colors[v])
                return false;
        return true;
    }
}
