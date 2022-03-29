package Q187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for(int i=0; i + 9 < s.length(); i++){
            if(seen.contains(s.substring(i, i+10)))
                res.add(s.substring(i, i+10));
            else
                seen.add(s.substring(i, i+10));
        }
        return new ArrayList<>(res);
    }
}
