package Q283;

public class Solution {

    public void moveZeroes(int[] nums) {

        int k = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                if(i != k){
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                    k ++;
                }
                else
                    k++;
            }
        }
    }
}
