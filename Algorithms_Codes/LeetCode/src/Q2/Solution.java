package Q2;

// 这是自己的思路
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1.val == 0 && l1.next == null)
            return l2;
        if(l2.val == 0 && l2.next == null)
            return l1;

        int[] arr1 = listToArr(l1);
        int[] arr2 = listToArr(l2);
        int[] arr = sumArr(arr1, arr2);
        return arrToList(arr);
    }

    private int[] sumArr(int[] arr1, int[] arr2){
        int[] arr = new int[101];
        int pre = 0;
        for(int i=0; i<101; i++){
            int sumi = arr1[i] + arr2[i] + pre;
            arr[i] = sumi % 10;
            sumi /= 10;
            pre = sumi;
        }
        return arr;
    }

    private int[] listToArr(ListNode l){
        int[] arr = new int[101];
        int i = 0;
        while(l != null){
            arr[i ++] = l.val;
            l = l.next;
        }
        return arr;
    }

    private ListNode arrToList(int[] arr){
        int i;
        for(i = arr.length - 1; i >= 0; i --){
            if(arr[i] != 0)
                break;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode curNode = dummyHead;
        for(int j=0; j <= i; j++){
            curNode.next = new ListNode(arr[j]);
            curNode = curNode.next;
        }
        return dummyHead.next;
    }
}
