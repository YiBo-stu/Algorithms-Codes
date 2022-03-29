package Q980;

// practice
public class Solution2 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int R, C;
    private int start, end;

    public int uniquePathsIII(int[][] grid) {

        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int left = R * C;

        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                if(grid[i][j] == -1)
                    left --;
                else if(grid[i][j] == 1){
                    grid[i][j] = 0;
                    start = i * C + j;
                }
                else if(grid[i][j] == 2){
                    grid[i][j] = 0;
                    end = i * C + j;
                }

        return dfs(start, 0, left);
    }

    private int dfs(int v, int visited, int left){
        visited = visited + (1 << v);
        left --;
        if(end == v && left == 0)
            return 1;
        if(end == v && left != 0)
            return 0;
        int res = 0;
        int curx = v / C, cury = v % C;
        for(int k=0; k<4; k++){
            int nextx = curx + dirs[k][0];
            int nexty = cury + dirs[k][1];
            int next = nextx * C + nexty;
            if(inArea(nextx, nexty) && (visited & (1 << next)) == 0 && grid[nextx][nexty] == 0){
                res += dfs(next, visited, left);
            }
        }
        return res;
    }

    private boolean inArea(int x, int y){
        return x >=0 && x < R && y >= 0 && y < C;
    }
}
