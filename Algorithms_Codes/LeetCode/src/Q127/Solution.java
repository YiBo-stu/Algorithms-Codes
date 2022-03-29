package Q127;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    private class Pair{
        public String word;
        public int step;
        public Pair(String word, int step){
            this.word = word;
            this.step = step;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Queue<Pair> q = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        q.add(new Pair(beginWord, 1));
        set.add(beginWord);
        while(!q.isEmpty()){
            Pair cur = q.remove();
            for(String word: wordList){
                if(!set.contains(word) && diff(cur.word, word)){
                    if(word.equals(endWord))
                        return cur.step + 1;
                    q.add(new Pair(word, cur.step + 1));
                    set.add(word);
                }
            }
        }
        return 0;
    }

    private boolean diff(String s, String t){
        int count = 0;
        for(int i=0; i<s.length(); i++)
            if(s.charAt(i) != t.charAt(i))
                if(++ count > 1)
                    return false;
        return count == 1;
    }
}
