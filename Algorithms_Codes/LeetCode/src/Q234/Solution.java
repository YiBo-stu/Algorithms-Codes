package Q234;

public class Solution {

    public boolean isPalindrome(ListNode head) {

        if(head.next == null) return true;
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            if(fast == null)
                slow = slow.next;
            else if(fast.next == null)
                slow = slow.next.next;
            else{
                ListNode delNode = slow.next;
                slow.next = delNode.next;
                delNode.next = head;
                head = delNode;
            }
        }
        while(slow != null){
            if(slow.val != head.val)
                return false;
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
}
