package Q62;

import java.util.Arrays;

public class Solution {

    private int[][] dirs = {{0, 1}, {1, 0}};
    private int R, C;
    private int[] memo;

    public int uniquePaths(int m, int n) {

        R = m;
        C = n;
        memo = new int[R * C];
        Arrays.fill(memo, -1);
        return dfs(0);
    }

    private int dfs(int v){

        if(memo[v] != -1)
            return memo[v];
        if(v == R * C - 1)
            return 1;
        int res = 0;
        int curx = v / C, cury = v % C;
        for(int k=0; k<2; k++){
            int nextx = curx + dirs[k][0];
            int nexty = cury + dirs[k][1];
            int next = nextx * C + nexty;
            if(inArea(nextx, nexty)){
                res += dfs(next);
            }
        }
        memo[v] = res;
        return res;
    }

    private boolean inArea(int x, int y){
        return x >=0 && x < R && y >= 0 && y < C;
    }
}
