package Q71;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {

    public String simplifyPath(String path) {

        String[] strings = path.split("/");
        Deque<String> deque = new LinkedList<>();
        for(String s: strings){
            if(s.equals(""))
                continue;
            if(s.equals(".")){}
            else if(s.equals("..")){
                if(deque.isEmpty()){}
                else
                    deque.removeLast();
            }
            else{
                deque.addLast(s);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("/");
        while(!deque.isEmpty()){
            res.append(deque.removeFirst());
            if(!deque.isEmpty())
                res.append("/");
        }
        return res.toString();
    }

}

