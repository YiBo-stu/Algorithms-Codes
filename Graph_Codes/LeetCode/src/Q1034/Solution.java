package Q1034;

public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private boolean[][] visited;
    private boolean[][] isBorder;
    private int R, C;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {

        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        visited = new boolean[R][C];
        isBorder = new boolean[R][C];

        // DFS
        dfs(row, col);
        for(int x=0; x<R; x++)
            for(int y=0; y<C; y++)
                if(isBorder[x][y])
                    grid[x][y] = color;
        return grid;
    }

    private void dfs(int x, int y){
        visited[x][y] = true;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(!inArea(nextx, nexty) || grid[nextx][nexty] != grid[x][y]){
                isBorder[x][y] = true;
            }
            else if(!visited[nextx][nexty]){
                dfs(nextx, nexty);
            }
        }
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
