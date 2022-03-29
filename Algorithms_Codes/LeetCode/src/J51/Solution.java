package J51;

import java.util.Arrays;

public class Solution {

    public int reversePairs(int[] nums) {
//        int[] temp = Arrays.copyOf(nums, nums.length);
//        return mergeSort(nums, 0, nums.length - 1, temp);
        return mergeSortBU(nums);
    }

    private int mergeSort(int[] nums, int l, int r, int[] temp){

        if(l >= r) return 0;
        int res = 0;
        int mid = l + (r - l) / 2;
        res += mergeSort(nums, l, mid, temp);
        res += mergeSort(nums, mid + 1, r, temp);
        if(nums[mid] > nums[mid + 1]){
            res += merge(nums, l, mid, r, temp);
        }
        return res;
    }

    private int mergeSortBU(int[] nums){
        int[] temp = Arrays.copyOf(nums, nums.length);
        int res = 0;
        for(int size = 1; size < nums.length; size *= 2){
            for(int i = 0; i + size < nums.length; i += size * 2){
                // [i, i + size - 1], [i + size, i + size * 2 - 1]
                if(nums[i + size - 1] > nums[i + size]){
                    res += merge(nums, i, i + size - 1, Math.min(i + size * 2 - 1, nums.length - 1), temp);
                }
            }
        }
        return res;
    }

    private int merge(int[] nums, int l, int mid, int r, int[] temp){

        System.arraycopy(nums, l, temp, l, r - l + 1);
        int i = l, j = mid + 1;
        int res = 0;
        for(int k = l; k <= r; k++){
            if(i > mid){
                nums[k] = temp[j ++];
            }
            else if(j > r){
                nums[k] = temp[i ++];
            }
            else if(temp[i] <= temp[j]){
                nums[k] = temp[i ++];
            }
            else{
                nums[k] = temp[j ++];
                res += mid - i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 3, 1};
        System.out.println((new Solution().reversePairs(arr)));
    }
}
