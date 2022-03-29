package Q187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// 滚动哈希法，哈希值的计算使用四进制
public class Solution3 {
    public List<String> findRepeatedDnaSequences(String s){
        if(s.length() < 10) return new ArrayList<>();
        HashSet<Integer> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        int[] map = new int[256];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;
        int hash = 0, four9 = (int)Math.pow(4, 9);
        for(int i=0; i<9; i++)
            hash = hash * 4 + map[s.charAt(i)];
        for(int i=9; i<s.length(); i++){
            hash = hash * 4 + map[s.charAt(i)];
            if(seen.contains(hash))
                res.add(s.substring(i - 9 ,i + 1));
            else
                seen.add(hash);
            hash -= map[s.charAt(i - 9)] * four9;
        }
        return new ArrayList<>(res);
    }
}
