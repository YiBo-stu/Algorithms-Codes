package Q488;

import java.util.ArrayList;

public class Solution {

    private class Pair{
        public char color;
        public int num;
        public Pair(char color, int num){
            this.color = color;
            this.num = num;
        }
    }

    public int findMinStep(String board, String hand) {

        char lastColor = board.charAt(0);
        ArrayList<Pair> arr = new ArrayList<>();
        arr.add(new Pair(board.charAt(0), 1));
        for(int i = 1; i < board.length(); i ++){
            if(board.charAt(i) == lastColor){
                arr.set(arr.size() - 1, new Pair(lastColor, arr.get(arr.size() - 1).num + 1));
            }
            else{
                arr.add(new Pair(board.charAt(i), 1));
            }
        }
        return 0;
    }
}
