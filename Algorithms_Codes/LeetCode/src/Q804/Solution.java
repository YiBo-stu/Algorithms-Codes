package Q804;

import java.util.TreeSet;

public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-", ".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set = new TreeSet<>();

        for(String str: words){
            StringBuilder res = new StringBuilder();
            for(int i=0; i<str.length(); i++){
                // 这样写比较优雅
                res.append(codes[str.charAt(i)-'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(new Solution().uniqueMorseRepresentations(words));
    }
}
