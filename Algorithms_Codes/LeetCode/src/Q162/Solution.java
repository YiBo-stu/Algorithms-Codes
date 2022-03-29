package Q162;

public class Solution {

    public int findPeakElement(int[] nums) {

        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(mid - 1 >= l && nums[mid - 1] > nums[mid])
                r = mid - 1;
            else if(mid + 1 < r && nums[mid] < nums[mid + 1])
                l = mid + 1;
            else
                return mid;
        }
        return l;
    }
}
