package Q4;

public class Solution2 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2){

        int m = nums1.length, n = nums2.length;
        int k = (m + n) % 2 == 0 ? (m + n) / 2 - 1 : (m + n) / 2;
        int i = 0, j = 0, mid = 0, mid2 = 0;
        for(int t = 0; t <= k; t ++){
            if(i >= nums1.length)
                mid = nums2[j ++];
            else if(j >= nums2.length)
                mid = nums1[i ++];
            else if(nums1[i] <= nums2[j])
                mid = nums1[i ++];
            else
                mid = nums2[j ++];
        }
        if((m + n) % 2 == 0){
            if(i >= nums1.length)
                mid2 = nums2[j];
            else if(j >= nums2.length)
                mid2 = nums1[i];
            else mid2 = Math.min(nums1[i], nums2[j]);
        }
        return (m + n) % 2 == 0 ? (mid + mid2) * 0.5 : mid;
    }
}
