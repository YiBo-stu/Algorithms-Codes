package Q167;

public class Solution {
    // 用二分查找法求解，时间复杂度是O(nlogn)

    public int[] twoSum(int[] numbers, int target) {

        for(int i=0; i<numbers.length; i++){
            int val = target - numbers[i];
            int res = binarySearch(numbers, i + 1, numbers.length - 1, val);
            if(res >= 0)
                return new int[]{i + 1, res + 1};
        }
        return null;
    }

    private int binarySearch(int[] numbers, int l, int r, int val){
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(numbers[mid] == val)
                return mid;
            if(numbers[mid] < val)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }
}
