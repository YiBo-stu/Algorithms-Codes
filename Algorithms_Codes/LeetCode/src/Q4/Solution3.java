package Q4;

// 使用二分搜索法，这里是递归写法，也可以用非递归
public class Solution3 {

    // 这里第k小的数字，k是从1开始算的
    public double findMedianSortedArrays(int[] nums1, int[] nums2){

        int m = nums1.length, n = nums2.length;
        int totalLength = m + n;
        if(totalLength % 2 == 0){
            int mid1 = getK(nums1, 0, m - 1, nums2, 0, n - 1, totalLength / 2);
            int mid2 = getK(nums1, 0, m - 1, nums2, 0, n - 1, totalLength / 2 + 1);
            return (mid1 + mid2) * 0.5;
        }
        else
            return getK(nums1, 0, m - 1, nums2, 0, n - 1, totalLength / 2 + 1);
    }

    private int getK(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k){

        if(k == 1){
            if(l1 > r1) return nums2[l2];
            if(l2 > r2) return nums1[l1];
            return Math.min(nums1[l1], nums2[l2]);
        }
        int len1 = r1 - l1 + 1, len2 = r2 - l2 + 1;
        if(len1 == 0) return nums2[l2 + k - 1];
        if(len2 == 0) return nums1[l1 + k - 1];
        int half = k / 2;
        int p1 = len1 < half ? nums1[r1] : nums1[l1 + half - 1];
        int p2 = len2 < half ? nums2[r2] : nums2[l2 + half - 1];
        if(p1 <= p2){
            l1 += Math.min(len1, half);
            k -= Math.min(len1, half);
        }
        else{
            l2 += Math.min(len2, half);
            k -= Math.min(len2, half);
        }

        return getK(nums1, l1, r1, nums2, l2, r2, k);
    }

    public static void main(String[] args) {
        int[] arr1 = {1};
        int[] arr2 = {3};
        System.out.println(new Solution3().findMedianSortedArrays(arr1, arr2));
    }
}
