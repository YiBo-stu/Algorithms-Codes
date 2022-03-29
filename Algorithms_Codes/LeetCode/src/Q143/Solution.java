package Q143;

public class Solution {
    // 递归的写法，效率太低

    public void reorderList(ListNode head) {

        if(head.next == null || head.next.next == null)
            return;

        ListNode last2 = head;
        while(last2.next.next != null)
            last2 = last2.next;
        ListNode temp = head.next;
        head.next = last2.next;
        head.next.next = temp;
        last2.next = null;
        head = head.next.next;

        reorderList(head);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        new Solution().reorderList(head);
    }
}
