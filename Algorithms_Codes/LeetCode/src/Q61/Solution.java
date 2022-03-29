package Q61;

public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;

        int numNodes = 0;
        ListNode endNode = null;
        for(ListNode node = head; node != null; node = node.next){
            numNodes ++;
            endNode = node;
        }
        int rotate = k % numNodes;
        if(rotate == 0)
            return head;
        ListNode pre = head, cur;
        for(int i=0; i < numNodes - rotate - 1; i++){
            pre = pre.next;
        }
        cur = pre.next;
        pre.next = null;
        endNode.next = head;
        return cur;
    }
}
