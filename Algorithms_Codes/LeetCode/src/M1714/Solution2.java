package M1714;

import java.util.Arrays;
import java.util.Random;

public class Solution2 {

    public int[] smallestK(int[] arr, int k) {

        if(arr.length == 0 || k == 0)
            return new int[0];

        Random rnd = new Random();
        sort3ways(arr, 0, arr.length - 1, rnd, k);
        return Arrays.copyOf(arr, k);
    }

    private void sort3ways(int[] arr, int l, int r, Random rnd, int k){

        if(l >= r) return;
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);
        // [l + 1, lt] < v, [lt + 1, i - 1] == v, [gt, r] > v
        int lt = l, gt = r + 1, i = l + 1;
        while(i < gt){
            if(arr[i] < arr[l])
                swap(arr, ++ lt, i ++);
            else if(arr[i] > arr[l])
                swap(arr, i, -- gt);
            else
                i ++;
        }
        swap(arr, l, lt);
        if(lt < k - 1)
            sort3ways(arr, lt + 1, r, rnd, k);
        else if(lt > k - 1)
            sort3ways(arr, l, lt - 1, rnd, k);
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
