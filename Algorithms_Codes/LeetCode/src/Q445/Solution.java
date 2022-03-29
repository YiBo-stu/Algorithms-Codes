package Q445;

import java.util.Stack;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while(l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode dummyHead = new ListNode(0);
        int carry = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            int a = stack1.isEmpty()? 0: stack1.pop();
            int b = stack2.isEmpty()? 0: stack2.pop();
            int sum = a + b + carry;
            dummyHead.next = new ListNode(sum % 10, dummyHead.next);
            carry = sum / 10;
        }
        return dummyHead.next;
    }
}
