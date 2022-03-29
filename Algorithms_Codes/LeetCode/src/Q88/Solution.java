package Q88;

import java.util.Arrays;

public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1.length == 0)
            return;
        int[] temp = Arrays.copyOf(nums1, nums1.length);
        System.arraycopy(nums2, 0, temp, m, n);
        for(int t: temp)
            System.out.print(t);
        int i=0, j=m;
        for(int k=0; k<nums1.length; k++){
            if(i >= m){
                nums1[k] = temp[j ++];
            }
            else if(j >= m + n){
                nums1[k] = temp[i ++];
            }
            else if(temp[i] <= temp[j]){
                nums1[k] = temp[i ++];
            }
            else{
                nums1[k] = temp[j ++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        new Solution().merge(nums1, 3, nums2, 3);
    }
}
