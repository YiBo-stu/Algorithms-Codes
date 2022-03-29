package Q209;

public class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int l = 0, r = -1;
        int sum = 0;
        int res = nums.length + 1;

        while(l < nums.length){
            if(r + 1 < nums.length && sum < target)
                sum += nums[++ r];
            else{
                sum -= nums[l ++];
            }
            if(sum >= target){
                res = Math.min(res, r - l + 1);
            }
        }

        if(res == nums.length + 1)
            res = 0;
        return res;
    }

}
