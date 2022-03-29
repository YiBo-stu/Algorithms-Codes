package Q82;

public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode dummyHead = new ListNode(0, head);
        ListNode pre = dummyHead;
        ListNode cur = head;

        while(cur != null){
            while(cur.next != null && cur.val == cur.next.val)
                cur = cur.next;
            if(pre.next == cur)
                pre = cur;
            else{
                pre.next = cur.next;
            }
            cur = cur.next;
        }

        return dummyHead.next;
    }
}
