package Q529;

public class Solution {

    private int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    private char[][] board;
    private boolean[][] visited;
    private int R, C;
    private int[][] count;

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        this.board = board;
        R = board.length;
        C = board[0].length;
        visited = new boolean[R][C];
        count = new int[R][C];

        dfs(click[0], click[1]);
        return board;

    }

    private void dfs(int x, int y){
        visited[x][y] = true;
        int bombs = bombs(x, y);
        if(bombs == 0){
            board[x][y] = 'B';
            for(int d=0; d<8; d++){
                int nextx = x + dirs[d][0];
                int nexty = y + dirs[d][1];
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && board[nextx][nexty] != 'M')
                    dfs(nextx, nexty);
            }
        }
        else{
            board[x][y] = Character.forDigit(bombs, 10);
        }
    }

    private int bombs(int x, int y){
        int res = 0;
        for(int d=0; d<8; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if(inArea(nextx, nexty) && board[nextx][nexty] == 'M')
                res ++;
        }
        return res;
    }

    private boolean inArea(int x, int y){
        return x >=0 && x < R && y >= 0 && y < C;
    }
}
