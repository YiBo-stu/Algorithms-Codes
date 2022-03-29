package Q1020;

public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int R, C;
    private boolean[][] visited;
    private int count = 0;
    private boolean onBorder;

    public int numEnclaves(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        visited = new boolean[R][C];
        for(int x=0; x<R; x++){
            for(int y=0; y<C; y++){
                if(!visited[x][y] && grid[x][y]==1){
                    onBorder = false;
                    int temp = dfs(x, y);
                    if(!onBorder)
                        count += temp;
                }
            }
        }
        return count;
    }

    private int dfs(int x, int y){
        visited[x][y] = true;
        int res = 1;
        if(onBorder(x, y))
            onBorder = true;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty]==1){
                res += dfs(nextx, nexty);
            }
        }
        return res;
    }

    private boolean onBorder(int x, int y){
        return x == 0 || x == R - 1 || y == 0 || y == C - 1;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(new Solution().numEnclaves(grid));
    }

}