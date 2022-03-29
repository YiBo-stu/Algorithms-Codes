package Q1392;

// 暴力解法，提交超时
class Solution {
    public String longestPrefix(String s) {

        // [0, len - 1] equals [s.length() - len, s.length() - 1]
        for(int len=s.length()-1; len>=1; len--)
            if(equal(s, 0, len - 1, s.length() - len, s.length() - 1))
                return s.substring(0, len);
        return "";
    }
    private boolean equal(String s, int l1, int r1, int l2, int r2){
        for(int i=l1, j=l2; i<=r1; i++, j++)
            if(s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }
}