package Q203;

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        // 要求解的最基本的的问题
        if(head == null)
            return head;
        // 把原问题转化为更小的问题，要注意递归的宏观语义
        head.next = removeElements(head.next, val);
        return head.val==val? head.next: head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 6);
        System.out.println(res);
    }
}
