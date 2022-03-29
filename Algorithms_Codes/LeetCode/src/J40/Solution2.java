package J40;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution2 {
    public int[] getLeastNumbers(int[] arr, int k){
        // 使用优先队列解决topK问题
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<k; i++)
            q.add(arr[i]);
        for(int i=k; i<arr.length; i++){
            if(!q.isEmpty() && arr[i] < q.peek()){
                q.remove();
                q.add(arr[i]);
            }
        }
        int[] res = new int[k];
        for(int i=0; i<k; i++)
            res[i] = q.remove();
        return res;
    }
}
