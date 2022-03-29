package Q125;

public class Solution {
    // 把字母和数字提取出来

    public boolean isPalindrome(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c))
                sb.append(Character.toLowerCase(c));
        }
        int i = 0, j = sb.length() - 1;
        while(i < j){
            if(sb.charAt(i) != (sb.charAt(j)))
                return false;
            i ++;
            j --;
        }
        return true;
    }
}
