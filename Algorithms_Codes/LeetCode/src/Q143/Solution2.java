package Q143;

public class Solution2 {
    // 先找到中点，后半段反转，最后再合并

    public void reorderList(ListNode head) {

        if(head.next == null || head.next.next == null)
            return;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow.next;
        slow.next = null;

        ListNode pre = null;
        ListNode cur = mid;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        mid = pre;

        ListNode l1 = head, l2 = mid;
        while(l1 != null && l2 != null){
            ListNode l1_next = l1.next;
            ListNode l2_next = l2.next;
            l1.next = l2;
            l1 = l1_next;
            l2.next = l1;
            l2 = l2_next;
        }
    }
}
