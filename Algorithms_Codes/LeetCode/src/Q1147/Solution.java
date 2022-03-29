package Q1147;

// 不使用哈希的思想来解决
public class Solution {
    public int longestDecomposition(String text) {
        return solve(text, 0, text.length() - 1);
    }

    private int solve(String text, int l, int r){
        if(l > r) return 0;
        for(int i=l, j=r; i < j; i++, j--){
            if(equal(text, l, i, j, r))
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
