package Q827;


// 超出时间限制
public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private boolean[][] visited;
    private int L;
    private int maxIsland = 0;
    private int area;

    public int largestIsland(int[][] grid) {

        L = grid.length;
        if(L == 1) return 1;
        boolean allOnes = true;
        for(int i=0; i<L; i++)
            for(int j=0; j<L; j++)
                if(grid[i][j] == 0){
                    allOnes = false;
                    break;
                }
        if(allOnes) return L * L;
        boolean allZeros = true;
        for(int i=0; i<L; i++)
            for(int j=0; j<L; j++)
                if(grid[i][j] == 1){
                    allZeros = false;
                    break;
                }
        if(allZeros) return 1;

        this.grid = grid;
        for(int x=0; x<L; x++){
            for(int y=0; y<L; y++){
                if(grid[x][y] == 0){
                    int adj = 0;
                    for(int d=0; d<4; d++){
                        int nextx = x+dirs[d][0];
                        int nexty = y+dirs[d][1];
                        if(inArea(nextx, nexty) && grid[nextx][nexty]==1)
                            adj ++;
                    }
                    if(adj >= 1){
                        grid[x][y] = 1;
                        // dfs
                        visited = new boolean[L][L];
                        for(int i=0; i<L; i++){
                            for(int j=0; j<L; j++){
                                if(!visited[i][j] && grid[i][j] == 1){
                                    area = 0;
                                    dfs(i, j);
                                    maxIsland = Math.max(maxIsland, area);
                                }
                            }
                        }
                        grid[x][y] = 0;
                    }
                }
            }
        }
        return maxIsland;
    }

    private void dfs(int x, int y){
        visited[x][y] = true;
        area ++;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 1){
                dfs(nextx, nexty);
            }
        }
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<L && y>=0 && y<L;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0}, {0, 1}};
        System.out.println(new Solution().largestIsland(grid));
    }

}
