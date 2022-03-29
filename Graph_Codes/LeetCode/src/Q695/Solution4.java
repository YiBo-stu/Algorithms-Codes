package Q695;

// 通过测试，但代码可以再优化，都用二维处理就行了，没必要来回切换
public class Solution4 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] G;
    private int R, C;
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) return 0;
        R = grid.length;
        if(R == 0) return 0;
        C = grid[0].length;
        if(C == 0) return 0;

        this.G = grid;
        visited = new boolean[R * C];

        int res = 0;
        for(int v=0; v<R*C; v++){
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
        int x = v / C, y = v % C;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            int next = nextx * C + nexty;
            if(inArea(nextx, nexty) && !visited[next] && G[nextx][nexty]==1){
                res += dfs(next);
            }
        }
        return res;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
