package Q20;

import java.util.Stack;

public class Solution {
    // 如果要使用之前自己定义的ArrayStack类，需要把ArrayStack和它依托的Array、以及接口Stack作为内部类提交（可以是private的）
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c=='(' || c=='[' || c=='{'){
                stack.push(c);
            }
            else {
                if(stack.isEmpty())
                    return false;
                char topChar = stack.pop();
                if(topChar=='(' && c!=')')
                    return false;
                if(topChar=='[' && c!=']')
                    return false;
                if(topChar=='{' && c!='}')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    // main函数在提交时候不需要删除，没有影响，但是isValid方法一定得是public的，leetcode会创建自己的main类运行程序
    public static void main(String[] args) {
        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("([)]"));
    }
}
