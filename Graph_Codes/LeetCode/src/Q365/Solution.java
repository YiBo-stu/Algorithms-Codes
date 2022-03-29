package Q365;

import java.util.*;

class tuple{
    public int x, y;
    public tuple(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((tuple)obj).x && this.y == ((tuple)obj).y;
    }
}

public class Solution {

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {

        HashSet<tuple> visited = new HashSet<>();
        Queue<tuple> queue = new LinkedList<>();
        queue.add(new tuple(0, 0));
        visited.add(new tuple(0, 0));
        while(!queue.isEmpty()){
            tuple cur = queue.remove();
            // 将当前状态能到达的状态添加到nexts中
            ArrayList<tuple> nexts = new ArrayList<>();
            nexts.add(new tuple(jug1Capacity, cur.y));
            nexts.add(new tuple(cur.x, jug2Capacity));
            nexts.add(new tuple(0, cur.y));
            nexts.add(new tuple(cur.x, 0));
            int min1 = Math.min(cur.x, jug2Capacity - cur.y);
            nexts.add(new tuple(cur.x - min1, cur.y + min1));
            int min2 = Math.min(jug1Capacity - cur.x, cur.y);
            nexts.add(new tuple(cur.x + min2, cur.y - min2));
            for(tuple next: nexts){
                if(!visited.contains(next)){
                    queue.add(next);
                    visited.add(next);
                    if(next.x == targetCapacity || next.y == targetCapacity
                            || next.x + next.y == targetCapacity)
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().canMeasureWater(34, 5, 6));
    }
}
