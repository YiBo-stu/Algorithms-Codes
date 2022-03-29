package Q752;

import java.util.*;

public class Solution {

    public int openLock(String[] deadends, String target) {

        HashSet<String> deadset = new HashSet<>();
        Collections.addAll(deadset, deadends);
        if(target.equals("0000")) return 0;
        if(deadset.contains("0000")) return -1;

        // BFS
        Queue<String> queue = new LinkedList<>();
        // 本质只需要记录某个节点是否被访问过，不一定非得使用数组来记录
        HashMap<String, Integer> visited = new HashMap<>();
        queue.add("0000");
        visited.put("0000", 0);
        while(!queue.isEmpty()){
            String cur = queue.remove();
            char[] chars = cur.toCharArray();
            ArrayList<String> nexts = new ArrayList<>();
            // 计算从当前状态可以达到的下一个状态
            for(int i=0; i<4; i++){
                char o = chars[i];
                chars[i] = Character.forDigit((chars[i] - '0' + 1) % 10, 10);
                nexts.add(new String(chars));
                chars[i] = o;
                // 对复数求余在不同的语言中结果是不同的，这里使用个小技巧来规避这一点
                chars[i] = Character.forDigit((chars[i] - '0' + 9) % 10, 10);
                nexts.add(new String(chars));
                chars[i] = o;
            }

            for(String next : nexts)
                if(!deadset.contains(next) && !visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    if(next.equals(target))
                        return visited.get(next);
                }
        }
        return -1;
    }
}
