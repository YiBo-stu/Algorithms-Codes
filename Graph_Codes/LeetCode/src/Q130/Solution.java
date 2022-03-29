package Q130;

import java.util.ArrayList;

// 这是自己的实现，比较麻烦
public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private char[][] board;
    private int R, C;
    private boolean[][] visited;
    private boolean onBorder;

    public void solve(char[][] board) {
        R = board.length;
        C = board[0].length;
        this.board = board;
        visited = new boolean[R][C];
        for(int x=0; x<R; x++){
            for(int y=0; y<C; y++){
                if(!visited[x][y] && board[x][y]=='O'){
                    onBorder = false;
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    dfs(x, y, arrayList);
                    if(!onBorder){
                        for(int i=0; i<=arrayList.size()-2; i+=2){
                            int tempx = arrayList.get(i);
                            int tempy = arrayList.get(i+1);
                            board[tempx][tempy] = 'X';
                        }
                    }
                }
            }
        }
    }

    private void dfs(int x, int y, ArrayList<Integer> arrayList){
        visited[x][y] = true;
        arrayList.add(x);
        arrayList.add(y);
        if(onBorder(x, y))
            onBorder = true;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && !visited[nextx][nexty] && board[nextx][nexty]=='O'){
                dfs(nextx, nexty, arrayList);
            }
        }
    }

    private boolean onBorder(int x, int y){
        return x == 0 || x == R - 1 || y == 0 || y == C - 1;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

}