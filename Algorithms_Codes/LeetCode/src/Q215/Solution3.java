package Q215;

import java.util.Random;

// 练习用快排的思想解决
public class Solution3 {

    public int findKthLargest(int[] nums, int k) {

        int position = nums.length - k;
        Random rnd = new Random();
        sort2ways(nums, 0, nums.length - 1, rnd, position);
        return nums[position];
    }

    private void sort2ways(int[] nums, int l, int r, Random rnd, int position){

        while(l <= r){
            int p = partition(nums, l, r, rnd);
            if(p == position) return;
            if(p < position)
                l = p + 1;
            if(p > position)
                r = p - 1;
        }

//        if(l >= r) return;
//        int p = partition(nums, l, r, rnd);
//        if(p == position) return;
//        if(p < position)
//            sort2ways(nums, p+1, r, rnd, position);
//        else
//            sort2ways(nums, l, p-1, rnd, position);
    }

    private int partition(int[] nums, int l, int r, Random rnd){

        int p = l + rnd.nextInt(r - l + 1);
        swap(nums, l, p);
        int i = l + 1, j = r;
        while(true){
            while(i <= j && nums[i] < nums[l])
                i ++;
            while(i <= j && nums[j] > nums[l])
                j --;
            if(i >= j)
                break;
            swap(nums, i ++, j --);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
