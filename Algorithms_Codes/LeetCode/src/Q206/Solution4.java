package Q206;

// 练习用
public class Solution4 {

    public ListNode reverseList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode cur = head;
        while(cur.next != null){
            ListNode delNode = cur.next;
            cur.next = delNode.next;
            delNode.next = dummyHead.next;
            dummyHead.next = delNode;
        }
        return dummyHead.next;
    }
}
