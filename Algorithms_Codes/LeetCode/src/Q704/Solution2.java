package Q704;

public class Solution2 {

    public int search(int[] nums, int target) {

        // 在data[l, r)中查找target
        int l = 0, r = nums.length;
        while(l < r){
            int mid = l + (r-1-l)/2;
            if(nums[mid] == target)
                return mid;
            if(nums[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }
        return -1;
    }

}
