package Q142;

public class Solution {

    public ListNode detectCycle(ListNode head) {

        if(head == null || head.next == null)
            return null;

        ListNode slow = head.next, fast = head.next.next;
        while(fast != slow){
            if(fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
