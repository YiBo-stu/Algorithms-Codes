package Q279;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int numSquares(int n) {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(n);
        visited[n] = true;
        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(; size>0; size--){
                int cur = queue.poll();
                for(int i=1; ; i++){
                    int next = cur - i * i;
                    if(next < 0) break;
                    if(!visited[next]){
                        queue.offer(next);
                        visited[next] = true;
                        if(next == 0)
                            return step + 1;
                    }
                }
            }
            step ++;
        }
        return step;
    }
}
