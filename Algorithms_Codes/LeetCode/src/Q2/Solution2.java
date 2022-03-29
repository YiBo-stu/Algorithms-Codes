package Q2;

public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(l1.val == 0 && l1.next == null)
            return l2;
        if(l2.val == 0 && l2.next == null)
            return l1;

        ListNode dummyHead = new ListNode(0);
        ListNode curNode = dummyHead;
        int carry = 0;

        while(l1 != null || l2 != null){
            int cur1 = l1 == null ? 0 : l1.val;
            int cur2 = l2 == null ? 0 : l2.val;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
            int curSum = cur1 + cur2 + carry;
            curNode.next = new ListNode(curSum % 10);
            curNode = curNode.next;
            carry = curSum / 10;
        }
        if(carry != 0)
            curNode.next = new ListNode(carry);
        return dummyHead.next;
    }
}
