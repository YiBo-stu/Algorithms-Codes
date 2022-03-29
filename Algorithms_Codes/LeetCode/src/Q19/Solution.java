package Q19;

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyHead = new ListNode(0, head);
        ListNode fast = dummyHead, slow = dummyHead;
        for(int i=0; i<n+1; i++)
            fast = fast.next;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}
