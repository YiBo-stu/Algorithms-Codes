package Q215;

import java.util.PriorityQueue;

public class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        // 使用优先队列解决215号问题
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i=0; i<k; i++){
            q.add(nums[i]);
        }
        for(int i=k; i<nums.length; i++){
            if(!q.isEmpty() && nums[i] > q.peek()){
                q.remove();
                q.add(nums[i]);
            }
        }
        return q.peek();
    }
}
