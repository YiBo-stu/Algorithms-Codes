package Q206;

public class Solution3 {
    // 头插法实现反转链表
    public ListNode reverseList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode p = dummyHead.next;
        while(p.next != null){
            ListNode delNode = p.next;
            p.next = delNode.next;
            delNode.next = dummyHead.next;
            dummyHead.next = delNode;
        }
        return dummyHead.next;
    }
}
