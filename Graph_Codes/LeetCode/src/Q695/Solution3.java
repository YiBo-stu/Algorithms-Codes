package Q695;

import java.util.HashSet;

// 自己练习用，与Solution中解答一致
public class Solution3 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private HashSet<Integer>[] G;
    private int[][] grid;
    private int R, C;
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {

        if(grid == null) return 0;
        R = grid.length;
        if(R == 0) return 0;
        C = grid[0].length;
        if(C == 0) return 0;
        this.grid = grid;
        G = constructGraph();
        visited = new boolean[G.length];
        int res = 0;
        for(int v=0; v<G.length; v++){
            // 这里出错了，需要留意
            int x = v / C, y = v % C;
            if(!visited[v] && grid[x][y] == 1){
                res = Math.max(res, dfs(v));
            }
        }
        return res;
    }

    private int dfs(int v){
        visited[v] = true;
        int res = 1;
        for(int w: G[v]){
            if(!visited[w]){
                res += dfs(w);
            }
        }
        return res;
    }

    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g = new HashSet[R * C];
        for(int i=0; i<g.length; i++)
            g[i] = new HashSet<>();
        for(int v=0; v<g.length; v++){
            int x = v / C, y = v % C;
            // 这里没写if语句，需要留意
            if(grid[x][y] == 1){
                for(int d=0; d<4; d++){
                    int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
                    if(inArea(nextx, nexty) && grid[nextx][nexty] == 1){
                        int nextv = nextx * C + nexty;
                        g[v].add(nextv);
                        g[nextv].add(v);
                    }
                }
            }
        }
        return g;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
