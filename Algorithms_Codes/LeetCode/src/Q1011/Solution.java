package Q1011;

public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int l = getMax(weights), r = sumWeights(weights);
        while(l < r){
            int mid = l + (r - l) / 2;
            if(getDays(weights, mid) <= days){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    private int getDays(int[] weights, int k){
        // 这里写的有些波折
        int cur = 0, days = 0;
        for(int weight: weights){
            if(cur + weight <= k){
                cur += weight;
            }else{
                days ++;
                cur = weight;
            }
        }
        days ++;
        return days;
    }

    private int sumWeights(int[] weights){
        int res = 0;
        for(int weight: weights)
            res += weight;
        return res;
    }

    private int getMax(int[] weights){
        int max = weights[0];
        for(int i=1; i<weights.length; i++){
            if(weights[i] > max)
                max = weights[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println(new Solution().getDays(arr, 5));
        System.out.println(new Solution().shipWithinDays(arr, 5));
    }
}
