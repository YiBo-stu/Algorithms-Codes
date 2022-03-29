package Q875;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = maxValue(piles);

        int l = 1, r = max;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(eatingTime(piles, mid) <= h){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    private int eatingTime(int[] piles, int k){
        int time = 0;
        for(int i: piles){
            time += piles[i] / k + (piles[i]%k==0? 0: 1);
        }
        return time;
    }

    private int maxValue(int[] piles){
        int max = piles[0];
        for(int i=1; i<piles.length; i++){
            if(piles[i] > max)
                max = piles[i];
        }
        return max;
    }
}
