package Q4;

// 练习使用二分法求解
public class Solution4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2){

        int m = nums1.length, n = nums2.length;
        int totalLength = m + n;
        if(totalLength % 2 == 0){
            int mid1 = getK(nums1, 0, m-1, nums2, 0, n-1, totalLength/2);
            int mid2 = getK(nums1, 0, m-1, nums2, 0, n-1, totalLength/2+1);
            return 0.5 * mid1 + 0.5 * mid2;
        }
        else
            return getK(nums1, 0, m-1, nums2, 0, n-1, totalLength/2+1);
    }

    private int getK(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k){
        if(k == 1){
            if(l1 > r1) return nums2[l2];
            if(l2 > r2) return nums1[l1];
            return Math.min(nums1[l1], nums2[l2]);
        }
        if(l1 > r1) return nums2[l2 + k - 1];
        if(l2 > r2) return nums1[l1 + k - 1];
        int len1 = r1 - l1 + 1;
        int len2 = r2 - l2 + 1;
        int half = k / 2;
        int num1 = len1 < half ? nums1[r1] : nums1[l1 + half - 1];
        int num2 = len2 < half ? nums2[r2] : nums2[l2 + half - 1];
        if(num1 < num2){
            l1 += Math.min(len1, half);
            k -= Math.min(len1, half);
        }
        else{
            l2 += Math.min(len2, half);
            k -= Math.min(len2, half);
        }
        return getK(nums1, l1, r1, nums2, l2, r2, k);
    }
}
