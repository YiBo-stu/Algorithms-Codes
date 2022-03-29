package Q25;

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode fast = head, slow = dummyHead;
        while(true){
            for(int i = 0; i < k; i ++){
                if(fast != null)
                    fast = fast.next;
                else
                    return dummyHead.next;
            }
            ListNode p = slow.next;
            for(int i = 1; i < k; i ++){
                ListNode delNode = p.next;
                p.next = delNode.next;
                delNode.next = slow.next;
                slow.next = delNode;
            }
            slow = p;
        }
    }
}
