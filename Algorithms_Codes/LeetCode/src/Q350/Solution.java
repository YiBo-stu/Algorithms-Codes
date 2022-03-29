package Q350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums1){
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        for(int num: nums2){
            if(map.containsKey(num)){
                res.add(num);
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }
        int[] ret = new int[res.size()];
        for(int i=0; i<res.size(); i++)
            ret[i] = res.get(i);
        return ret;
    }
}
