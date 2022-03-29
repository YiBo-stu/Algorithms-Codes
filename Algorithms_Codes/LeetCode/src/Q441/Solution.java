package Q441;

public class Solution {

    public int arrangeCoins(int n) {
        int l = 1, r = n;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            long num = (long) (1 + mid) * mid / 2;
            if(num < n)
                l = mid;
            else if(num > n)
                r = mid - 1;
            else
                return mid;
        }
        return l;
    }
    public static void main(String[] args) {
        int n = 1804289383;
        System.out.println(new Solution().arrangeCoins(n));
    }
}
