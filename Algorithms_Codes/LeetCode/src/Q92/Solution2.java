package Q92;

public class Solution2 {
    public ListNode reverseBetween(ListNode head, int left, int right){
        if(head == null || head.next == null || left == right)
            return head;

        int revNum = right - left;
        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;
        for(int i=0; i < left - 1; i++)
            pre = pre.next;
        ListNode cur = pre.next;
        while(revNum > 0){
            ListNode delNode = cur.next;
            cur.next = delNode.next;
            delNode.next = pre.next;
            pre.next = delNode;
            revNum --;
        }
        return dummyHead.next;
    }
}
