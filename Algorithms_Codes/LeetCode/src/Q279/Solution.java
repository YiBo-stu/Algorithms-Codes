package Q279;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private class Pair {
        public int num;
        public int step;
        public Pair(int num, int step){
            this.num = num;
            this.step = step;
        }
    }

    public int numSquares(int n) {

        boolean[] visited = new boolean[n + 1];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(n, 0));
        visited[n] = true;

        while(!q.isEmpty()){
            Pair cur = q.remove();
            for(int i=1; ; i ++){
                int next = cur.num - i * i;
                if(next < 0) break;
                // 这里把判断条件提前了，并且避免cur.num - i * i
                // 的重复计算，这个小优化能带来大约3倍的性能提升
                if(next == 0) return cur.step + 1;
                if(!visited[next]){
                    q.add(new Pair(next, cur.step + 1));
                    visited[next] = true;
                }
            }
        }

        throw new IllegalArgumentException("failed");
    }
}
