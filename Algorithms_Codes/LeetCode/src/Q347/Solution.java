package Q347;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {

    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for(int num: map.keySet()){
            if(pq.size() < k){
                pq.offer(num);
            }
            else{
                if(map.get(pq.peek()) < map.get(num)){
                    pq.poll();
                    pq.offer(num);
                }
            }
        }
        int[] res = new int[k];
        for(int i=0; i<k; i++)
            res[i] = pq.poll();
        return res;
    }
}
