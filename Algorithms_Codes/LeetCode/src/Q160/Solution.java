package Q160;

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null)
            return null;

        ListNode indexA = headA, indexB = headB;
        while(indexA != indexB){
            indexA = indexA == null? headB: indexA.next;
            indexB = indexB == null? headA: indexB.next;
        }
        return indexA;
    }
}
