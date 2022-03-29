package Q430;

import java.util.Stack;

public class Solution {

    Stack<Node> stack = new Stack<>();

    public Node flatten(Node head) {

        if(head == null) return null;
        Node cur = head;
        while(cur.next != null || cur.child != null || !stack.isEmpty()){
            if(cur.child != null){
                stack.push(cur.next);
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
                cur = cur.next;
            }
            else if(cur.next != null){
                cur = cur.next;
            }
            else{
                Node r = stack.pop();
                cur.next = r;
                if(r != null){
                    r.prev = cur;
                    cur = r;
                }
            }
        }
        return head;
    }
}
