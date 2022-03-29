package Q24;

public class Solution {

    public ListNode swapPairs(ListNode head) {

        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;
        while(pre.next != null && pre.next.next != null){
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;
            node1.next = node2.next;
            node2.next = node1;
            pre.next = node2;
            pre = node1;
        }
        return dummyHead.next;
    }
}
