package Q75;

public class Solution {
    public void sortColors(int[] nums) {

        // nums[0, lt] == 0, nums[lt+1, gt-1] == 1, nums[gt, nums.length-1] == 2
        int lt = -1, gt = nums.length, i = 0;
        while(i < gt){
            if(nums[i] == 0){
                lt ++;
                swap(nums, lt, i);
                i ++;
            }
            else if(nums[i] == 2){
                gt --;
                swap(nums, i, gt);
            }
            else
                i ++;
        }
    }

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
