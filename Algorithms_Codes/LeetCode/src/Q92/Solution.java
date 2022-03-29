package Q92;

public class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left == right)
            return head;
        if(left == 1){
            ListNode prev = head;
            ListNode cur = prev.next;
            for(int i=left+1; i<=right; i++){
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            head.next = cur;
            return prev;
        }else{
            ListNode node = head;
            for(int i=1; i<left-1; i++)
                node = node.next;
            ListNode prev = node.next;
            ListNode cur = prev.next;
            for(int i=left+1; i<=right; i++){
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            node.next.next = cur;
            node.next = prev;
            return head;
        }
    }
}
