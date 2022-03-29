package Q773;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int slidingPuzzle(int[][] board) {

        String initialState = boardToString(board);
        if(initialState.equals("123450")) return 0;

        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        queue.add(initialState);
        visited.put(initialState, 0);
        while(!queue.isEmpty()){
            String cur = queue.remove();
            ArrayList<String> nexts = getNexts(cur);

            for(String next: nexts){
                if(!visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if(next.equals("123450"))
                        return visited.get("123450");
                }
            }
        }
        return -1;
    }

    private ArrayList<String> getNexts(String cur){
        ArrayList<String> nexts = new ArrayList<>();
        char[] chars = cur.toCharArray();
        int i;
        for(i=0; i<6; i++)
            if(chars[i] == '0')
                break;
        int x = i / 3, y = i % 3;
        for(int d=0; d<4; d++){
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            int j = nextx * 3 + nexty;
            if(inArea(nextx, nexty)){
                swap(chars, i, j);
                nexts.add(new String(chars));
                swap(chars, i, j);
            }
        }
        return nexts;
    }

    private void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<2 && y>=0 && y<3;
    }

    private String boardToString(int[][] board){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<2; i++)
            for(int j=0; j<3; j++)
                res.append(board[i][j]);
        return res.toString();
    }

    public static void main(String[] args) {
        int[][] board ={{4, 1, 2}, {5, 0, 3}};
        System.out.println(new Solution().slidingPuzzle(board));
    }

}
