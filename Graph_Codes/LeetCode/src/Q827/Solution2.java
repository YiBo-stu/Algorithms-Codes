package Q827;

import java.util.Arrays;
import java.util.TreeSet;

public class Solution2 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int[][] visited;
    private int R, C;
    private int cccount = 0;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        visited = new int[R][C];
        for(int i=0; i<R; i++)
            Arrays.fill(visited[i], -1);
        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                if(grid[i][j] == 1 && visited[i][j] == -1){
                    int v = i * C + j;
                    dfs(v, cccount);
                    cccount ++;
                }
        int[] areas = new int[cccount];
        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                if(visited[i][j] != -1)
                    areas[visited[i][j]] ++;
        int maxArea = 0;
        for(int i=0; i<cccount; i++)
            maxArea = Math.max(maxArea, areas[i]);
        int[][] newGrid = new int[R][C];
        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                if(visited[i][j] != -1) {
                    newGrid[i][j] = areas[visited[i][j]];
                }
        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                if(newGrid[i][j] == 0){
                    int res = 1;
                    TreeSet<Integer> treeSet = new TreeSet<>();
                    for(int k=0; k<4; k++){
                        int nextx = i + dirs[k][0];
                        int nexty = j + dirs[k][1];
                        int next = nextx * C + nexty;
                        if(inArea(nextx, nexty) && visited[nextx][nexty] != -1 &&
                                !treeSet.contains(visited[nextx][nexty])){
                            res += newGrid[nextx][nexty];
                            treeSet.add(visited[nextx][nexty]);
                        }
                    }
                    maxArea = Math.max(maxArea, res);
                }
        return maxArea;
    }

    private void dfs(int v, int ccid){
        int x = v / C, y = v % C;
        visited[x][y] = ccid;
        for(int k=0; k<4; k++){
            int nextx = x + dirs[k][0];
            int nexty = y + dirs[k][1];
            int next = nextx * C + nexty;
            if(inArea(nextx, nexty) && grid[nextx][nexty] == 1 && visited[nextx][nexty] == -1)
                dfs(next, ccid);
        }
    }

    private boolean inArea(int x, int y){
        return x >=0 && x < R && y >= 0 && y < C;
    }

    public static void main(String[] args) {
        int[][] grid = {{1}};
        System.out.println(new Solution2().largestIsland(grid));
    }
}
