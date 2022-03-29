package Q206;

public class Solution2 {
    // 以递归的方式实现链表反转
    public ListNode reverseList(ListNode head) {
        // 求解基本问题
        if(head==null || head.next==null){
            return head;
        }
        // 拼接得到原问题的解
        ListNode rev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }
}
