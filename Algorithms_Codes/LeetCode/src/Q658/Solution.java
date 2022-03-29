package Q658;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> res = new ArrayList<>();
        int l = 0, r = arr.length - k - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(x - arr[mid] > arr[mid + k] - x){
                l = mid + 1;
            }
            else
                r = mid - 1;
        }
        for(int i=0; i<k; i++)
            res.add(arr[l + i]);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(new Solution().findClosestElements(arr, 4, 3));
    }
}