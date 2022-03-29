package Q147;

// 稍微改进一点
public class Solution2 {

    public ListNode insertionSortList(ListNode head) {

        if(head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(0, head);
        ListNode lastSorted = head;
        while(lastSorted.next != null){
            if(lastSorted.val <= lastSorted.next.val)
                lastSorted = lastSorted.next;
            else{
                ListNode p = dummyHead;
                while(p.next.val < lastSorted.next.val)
                    p = p.next;
                ListNode delNode = lastSorted.next;
                lastSorted.next = delNode.next;
                delNode.next = p.next;
                p.next = delNode;
            }
        }
        return dummyHead.next;
    }
}
