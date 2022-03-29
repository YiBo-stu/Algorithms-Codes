package Q187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// 滚动哈希法，哈希值的计算采用十进制
public class Solution2 {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() < 10)
            return new ArrayList<>();
        int[] map = new int[256];
        map['A'] = 1;
        map['C'] = 2;
        map['G'] = 3;
        map['T'] = 4;
        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        long hash = 0;
        for(int i=0; i<9; i++)
            hash = hash * 10 + map[s.charAt(i)];
        for(int i=9; i < s.length(); i++){
            hash = hash * 10 + map[s.charAt(i)];
            if(seen.contains(hash))
                res.add(s.substring(i-9, i+1));
            else
                seen.add(hash);
            hash -= map[s.charAt(i - 9)] * 1e9;
        }
        return new ArrayList<>(res);
    }
}