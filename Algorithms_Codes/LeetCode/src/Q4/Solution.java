package Q4;

// 使用merge解法
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length, n = nums2.length;
        int[] sorted = new int[m + n];
        merge(nums1, nums2, sorted);
        return (m + n) % 2 == 0 ? (sorted[(m + n) / 2] + sorted[(m + n) / 2 - 1]) * 0.5 : sorted[(m + n) / 2];
    }

    private void merge(int[] nums1, int[] nums2, int[] sorted){

        int i = 0, j = 0;
        for(int k = 0; k < sorted.length; k ++){
            if(i >= nums1.length)
                sorted[k] = nums2[j ++];
            else if(j >= nums2.length)
                sorted[k] = nums1[i ++];
            else if(nums1[i] <= nums2[j])
                sorted[k] = nums1[i ++];
            else
                sorted[k] = nums2[j ++];
        }
    }
}
