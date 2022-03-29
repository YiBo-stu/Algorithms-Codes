package Q150;

import java.util.Stack;

public class Solution {

    public int evalRPN(String[] tokens) {

        Stack<String> stack = new Stack<>();
        for(String s: tokens){
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int b = Integer.parseInt(stack.pop());
                int a = Integer.parseInt(stack.pop());
                int c;
                switch(s){
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(a - b));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(a / b));
                        break;
                    default:
                }
            }
            else{
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
