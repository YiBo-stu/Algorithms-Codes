package Q1091;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    private int R, C;

    public int shortestPathBinaryMatrix(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        if(grid[0][0] == 1) return -1;
        if(R==1 && C==1) return 1;
        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0][0] = true;
        dis[0][0] = 1;
        while(!queue.isEmpty()){
            int front = queue.remove();
            int x = front / C, y = front % C;
            for(int d=0; d<8; d++){
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty]==0){
                    queue.add(nextx * C + nexty);
                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[x][y] + 1;

                    if(nextx == R - 1 && nexty == C - 1)
                        return dis[nextx][nexty];
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
