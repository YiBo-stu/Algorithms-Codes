package Q438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()) return res;
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();

        int[] s_count = new int[26];
        int[] p_count = new int[26];

        for(int i = 0; i < pp.length; i ++){
            s_count[ss[i] - 'a'] ++;
            p_count[pp[i] - 'a'] ++;
        }
        if(Arrays.equals(s_count, p_count))
            res.add(0);
        for(int i=1, j=i+pp.length-1; j<ss.length; i++, j++){
            s_count[ss[i-1] - 'a'] --;
            s_count[ss[j] - 'a'] ++;
            if(Arrays.equals(s_count, p_count))
                res.add(i);
        }
        return res;
    }
}
