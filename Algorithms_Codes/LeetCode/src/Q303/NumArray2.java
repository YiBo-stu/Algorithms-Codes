package Q303;

public class NumArray2 {

    int[] sums;

    public NumArray2(int[] nums) {
        if(nums.length > 0){
            sums = new int[nums.length + 1];
            sums[0] = 0;
            for(int i=1; i<=nums.length; i++){
                sums[i] = sums[i-1] + nums[i-1];
            }
        }
    }

    public int sumRange(int left, int right) {
        return sums[right+1] - sums[left];
    }
}
