package Q20;

import java.util.Stack;

public class Solution2 {

    public boolean isValid(String s) {

        if(s.length() % 2 == 1)
            return false;
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c == '(')
                stack.push(')');
            else if(c == '[')
                stack.push(']');
            else if(c == '{')
                stack.push('}');
            else if(stack.isEmpty() || c != stack.pop())
                    return false;
        }
        return stack.isEmpty();
    }
}
