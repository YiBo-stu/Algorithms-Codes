package Q347;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution2 {

    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num: nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        int reverseK = nums.length - k;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(k <= reverseK)
                    return map.get(o1) - map.get(o2);
                else
                    return map.get(o2) - map.get(o1);
            }
        });
        for(int num: map.keySet()){
            if(k <= reverseK && pq.size() < k){
                pq.offer(num);
            }
            else if(k > reverseK && pq.size() < reverseK){
                pq.offer(num);
            }
            else{
                if(k <= reverseK){
                    if(map.get(pq.peek()) < map.get(num)){
                        pq.poll();
                        pq.offer(num);
                    }
                }
                else{
                    if(map.get(pq.peek()) < map.get(num)){
                        pq.poll();
                        pq.offer(num);
                    }
                }
            }
        }
        int[] res = new int[k];
        if(k <= reverseK)
            for(int i=0; i<k; i++)
                res[i] = pq.poll();
        else{
            for(int i=0; i<reverseK; i++)
                map.remove(pq.poll());
            ArrayList<Integer> arr = new ArrayList<>(map.keySet());
            for(int i=0; i<k; i++)
                res[i] = arr.get(i);
        }
        return res;
    }
}
