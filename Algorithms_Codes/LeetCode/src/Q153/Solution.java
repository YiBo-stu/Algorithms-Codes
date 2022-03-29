package Q153;

class Solution {
    public int findMin(int[] nums) {

        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] > nums[r]){
                l = mid + 1;
            }
            else{
                r = mid;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {

        int[] nums = {11, 13, 15, 17};
        System.out.println(new Solution().findMin(nums));
    }
}