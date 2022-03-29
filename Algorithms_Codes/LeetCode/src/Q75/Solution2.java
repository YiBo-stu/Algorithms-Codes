package Q75;

public class Solution2 {
    // 练习一遍

    public void sortColors(int[] nums) {

        int lt = -1, gt = nums.length, i = 0;
        while(i < gt){
            if(nums[i] == 0)
                swap(nums, ++lt, i++);
            else if(nums[i] == 2)
                swap(nums, --gt, i);
            else i++;
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
