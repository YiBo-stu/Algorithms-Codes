package Q147;

public class Solution {

    public ListNode insertionSortList(ListNode head) {

        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode cur = head, p;
        while(cur.next != null){
            p = dummyHead;
            while(p.next.val < cur.next.val){
                p = p.next;
            }
            if(p != cur){
                ListNode delNode = cur.next;
                cur.next = delNode.next;
                delNode.next = p.next;
                p.next = delNode;
            }
            else
                cur = cur.next;
        }
        return dummyHead.next;
    }
}
