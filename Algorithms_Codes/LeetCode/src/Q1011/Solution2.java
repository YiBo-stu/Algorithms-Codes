package Q1011;

import java.util.Arrays;

public class Solution2 {

    public int shipWithinDays(int[] weights, int days) {

        int l = Arrays.stream(weights).max().getAsInt();
        int r = Arrays.stream(weights).sum();

        while(l < r){
            int mid = l + (r - l) / 2;
            if(getDays(weights, mid) <= days){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }

    private int getDays(int[] weights, int k){

        int res = 0;
        int i = 0;
        while(i < weights.length){
            int sum = 0;
            while(i < weights.length && sum < k && sum + weights[i] <= k){
                sum += weights[i ++];
            }
            res ++;
        }
        return res;
    }

    private int getDays2(int[] weights, int k){

        int cur = 0, res = 0;
        for(int weight: weights){
            if(cur + weight <= k){
                cur += weight;
            }
            else{
                res ++;
                cur = weight;
            }
        }
        res ++;
        return res;
    }
}
