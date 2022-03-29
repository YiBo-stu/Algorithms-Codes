package Q155;

import java.util.Stack;

public class MinStack {

    private Stack<Long> stack;
    private Integer min;

    public MinStack() {

        stack = new Stack<>();
        min = null;
    }

    public void push(int val) {

        if(stack.isEmpty()){
            stack.push(0L);
            min = val;
        }
        else{
            stack.push(Long.valueOf(val) - min);
            min = Math.min(val, min);
        }
    }

    public void pop() {

        Long diff = stack.pop();
        if(diff >= 0){
            // return min + diff;
        }else{
            // int res = min;
            min = (int)(min - diff);
            // return res;
        }
    }

    public int top() {

        Long diff = stack.peek();
        if(diff >= 0)
            return (int)(min + diff);
        return min;
    }

    public int getMin() {

        return min;
    }
}