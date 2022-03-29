package Q752;

import java.util.*;

// 复习队列时候练习一遍
public class Solution2 {

    public int openLock(String[] deadends, String target) {

        HashSet<String> deadSet = new HashSet<>();
        Collections.addAll(deadSet, deadends);
        if(target.equals("0000")) return 0;
        if(deadSet.contains("0000")) return -1;

        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();
        queue.offer("0000");
        visited.put("0000", 0);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            ArrayList<String> nexts = new ArrayList<>();
            char[] chars = cur.toCharArray();
            for(int i=0; i<4; i++){
                char o = chars[i];
                chars[i] = Character.forDigit((o - '0' + 1) % 10, 10);
                nexts.add(new String(chars));
                chars[i] = Character.forDigit((o - '0' + 9) % 10, 10);
                nexts.add(new String(chars));
                chars[i] = o;
            }
            for(String next: nexts){
                if(!deadSet.contains(next) && !visited.containsKey(next)){
                    queue.offer(next);
                    visited.put(next, visited.get(cur) + 1);
                    if(next.equals(target))
                        return visited.get(next);
                }
            }
        }
        return -1;
    }
}
