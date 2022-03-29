package Q130;

// 这是参考题解里的方法写出来的，对于边界上的O及其联通的O，用深度优先遍历先填充为'#'，然后再进行一次循环改变值即可
public class Solution2 {
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;

    public void solve(char[][] board){
        R = board.length;
        C = board[0].length;
        for(int x=0; x<R; x++){
            for(int y=0; y<C; y++){
                if(onBorder(x, y) && board[x][y] == 'O'){
                    dfs(x, y, board);
                }
            }
        }
        for(int x=0; x<R; x++){
            for(int y=0; y<C; y++){
                if(board[x][y] == '#')
                    board[x][y] = 'O';
                // 这里开始没有写else，bug不容易发现
                else if(board[x][y] == 'O')
                    board[x][y] = 'X';
            }
        }
    }

    private void dfs(int x, int y, char[][] board){
        board[x][y] = '#';
        for(int d=0; d<4; d++){
            int nextx = x+dirs[d][0];
            int nexty = y+dirs[d][1];
            if(inArea(nextx, nexty) && board[nextx][nexty] == 'O')
                dfs(nextx, nexty, board);
        }
    }

    private boolean onBorder(int x, int y){
        return x == 0 || x == R - 1 || y == 0 || y == C - 1;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
