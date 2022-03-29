package FarmerPuzzle;

import java.util.*;

public class FarmerPuzzle {

    private HashMap<String, String> visited = new HashMap<>();

    public FarmerPuzzle(){

        HashSet<String> deadset = new HashSet<>();
        deadset.add("1000");
        deadset.add("1001");
        deadset.add("0110");
        deadset.add("0111");
        deadset.add("1100");
        deadset.add("0011");

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        visited.put("0000", "0000");
        while(!queue.isEmpty()){
            String cur = queue.remove();
            char[] chars = cur.toCharArray();
            ArrayList<String> nexts = new ArrayList<>();
            // nexts
            chars[0] = Character.forDigit(1 - (chars[0] - '0'), 10);
            nexts.add(new String(chars));
            for(int i=1; i<4; i++){
                char o = chars[i];
                chars[i] = Character.forDigit(1 - (chars[i] - '0'), 10);
                nexts.add(new String(chars));
                chars[i] = o;
            }

            for(String next: nexts){
                if(!deadset.contains(next) && !visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, cur);

                    if(next.equals("1111"))
                        return;
                }
            }
        }
        System.out.println(visited);
    }

    public Iterable<String> result(){

        ArrayList<String> res = new ArrayList<>();
        if(!visited.containsKey("1111"))
            return res;
        String cur = "1111";
        while(!cur.equals("0000")){
            res.add(cur);
            cur = visited.get(cur);
        }
        res.add("0000");
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FarmerPuzzle().result());
    }
}
