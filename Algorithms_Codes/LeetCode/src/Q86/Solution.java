package Q86;

public class Solution {

    public ListNode partition(ListNode head, int x) {

        if(head == null || head.next == null)
            return head;

        ListNode dummyHead = new ListNode(0, head);
        ListNode lt = dummyHead;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while(cur != null){
            if(cur.val < x){
                pre.next = cur.next;
                cur.next = lt.next;
                lt.next = cur;
                lt = lt.next;
                cur = pre.next;
            }
            else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
