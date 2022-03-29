package Q50;

public class Solution {
    public double myPow(double x, int n) {
        if(x == 0.0 && n == 0)
            throw new IllegalArgumentException("Illegal argument");
        if(n == 0) return 1;
        long p = n;
        double res = 1.0;
        p = Math.abs(p);
        while(p > 0){
            if((p & 1) == 1){
                res *= x;
            }
            x *= x;
            p >>= 1;
        }
        return n > 0 ? res : 1 / res;
    }
}
