package Q1147;

// 不使用哈希的思想来解决
// 这版代码的理论复杂度更优，但提交给OJ执行用时是更长的，因为数据规模太小
public class Solution2 {

    long MOD = (long)(1e9 + 7);
    long[] pow26;
    public int longestDecomposition(String text) {

        pow26 = new long[text.length()];
        pow26[0] = 1;
        for(int i=1; i<pow26.length; i++)
            pow26[i] = (pow26[i - 1] * 26) % MOD;
        return solve(text, 0, text.length() - 1);
    }

    private int solve(String text, int l, int r){

        if(l > r) return 0;
        long prehash = 0;
        long posthash = 0;
        for(int i=l, j=r; i < j; i++, j--){
            prehash = (prehash * 26 + (text.charAt(i) - 'a')) % MOD;
            posthash = (((text.charAt(j) - 'a') * pow26[r - j]) + posthash) % MOD;
            if(prehash == posthash && equal(text, l, i, j, r))
                return 2 + solve(text, i + 1, j - 1);
        }
        return 1;
    }

    private boolean equal(String text, int l1, int r1, int l2, int r2){
        for(int i=l1, j=l2; i <= r1; i++, j++)
            if(text.charAt(i) != text.charAt(j))
                return false;
        return true;
    }
}
