package Q454;

import java.util.HashMap;

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int res = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j ++) {
                map1.put((nums1[i] + nums2[j]), map1.getOrDefault((nums1[i] + nums2[j]), 0) + 1);
                map2.put((nums3[i] + nums4[j]), map2.getOrDefault((nums3[i] + nums4[j]), 0) + 1);
            }
        }
        for(int e: map1.keySet()) {
            res += map1.get(e) * map2.getOrDefault(-e, 0);
        }
        return res;
    }
}