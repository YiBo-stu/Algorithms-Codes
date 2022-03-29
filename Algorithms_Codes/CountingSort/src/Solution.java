
public class Solution {
    // LeetCode 75
    public void sortColors(int[] nums) {

        // 数据范围是[0, R)的计数排序
        int R = 3;

        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        for(int num: nums)
            cnt[num] ++;
        for(int i=0; i < R; i++){
            index[i + 1] = index[i] + cnt[i];
        }
        for(int i=0; i < R; i++){
            for(int j=index[i]; j<index[i+1]; j++)
                nums[j] = i;
        }
    }
}
