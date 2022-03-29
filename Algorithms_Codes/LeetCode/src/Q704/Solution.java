package Q704;

public class Solution {

    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length-1, target);
    }

    private int search(int[]data, int l, int r, int target){
        if(l > r)
            return -1;
        int mid = l + (r - l) / 2;
        if(data[mid] == target)
            return mid;
        else if(data[mid] > target)
            return search(data, l, mid-1, target);
        else
            return search(data, mid+1, r, target);
    }
}
