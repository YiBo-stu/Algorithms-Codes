package Q200;

public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private char[][] grid;
    private int R, C;
    private boolean[][] visited;
    private int count = 0;

    public int numIslands(char[][] grid) {
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        visited = new boolean[R][C];
        for(int x=0; x<R; x++){
            for(int y=0; y<C; y++){
                if(!visited[x][y] && grid[x][y]=='1'){
                    dfs(x, y);
                    count ++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y){
        visited[x][y] = true;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty]=='1'){
                dfs(nextx, nexty);
            }
        }
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

}
