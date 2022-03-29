package Q127;

import java.util.*;

// 把单词是否相差一个字母的比较过程进行了优化，是时间复杂度层面的优化
public class Solution2 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        visited.add(beginWord);
        int step = 1;
        while(!q.isEmpty()){
            int n = q.size();
            for(int k=0; k<n; k++){
                String cur = q.remove();
                char[] chars = cur.toCharArray();
                for(int i=0; i<beginWord.length(); i++){
                    char chari = chars[i];
                    for(char j = 'a'; j<='z'; j++){
                        if(j == chars[i]) continue;
                        chars[i] = j;
                        String nextWord = String.valueOf(chars);
                        if(wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                            if(nextWord.equals(endWord))
                                return step + 1;
                            q.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                    chars[i] = chari;
                }
            }
            step ++;
        }
        return 0;
    }
}
