package Q345;

public class Solution {

    public String reverseVowels(String s) {

        char[] chars = s.toCharArray();
        char[] vowel = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

        int i = 0, j = s.length() - 1;
        while(i < j){
            while(i < j && !contains(s.charAt(i), vowel))
                i ++;
            while(i < j && !contains(s.charAt(j), vowel))
                j --;
            if(i < j){
                char t = chars[i];
                chars[i] = chars[j];
                chars[j] = t;
                i ++;
                j --;
            }
        }

        return new String(chars);
    }

    private boolean contains(char c, char[] chars){
        for(char cc: chars)
            if(c == cc)
                return true;
        return false;
    }
}
