package Q875;

import java.util.Arrays;

public class Solution2 {

    public int minEatingSpeed(int[] piles, int h) {

        // 在LeetCode上用Arrays获取最大值反而更慢了
        int l = 1, r = Arrays.stream(piles).max().getAsInt();
        while(l < r){
            int mid = l + (r - l) / 2;
            if(eatingTime(piles, mid) <= h){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }

    private int eatingTime(int[] piles, int k){

        int time = 0;
        for(int n: piles){
            time += n % k == 0 ? n / k : (n / k + 1);
        }
        return time;
    }
}
