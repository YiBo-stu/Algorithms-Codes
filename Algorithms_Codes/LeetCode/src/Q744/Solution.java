package Q744;

public class Solution {

    public char nextGreatestLetter(char[] letters, char target) {

        int l = 0, r = letters.length;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(letters[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l == letters.length ? letters[0] : letters[l];
    }
}
