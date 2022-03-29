import com.sun.org.apache.xpath.internal.operations.Mod;

public class SubstringMatch {

    private SubstringMatch(){}

    public static int bruteforce(String s, String t){
        if(s.length() < t.length()) return -1;
        if(t.length() == 0) return 0;
        for(int i=0; i + t.length() - 1 < s.length(); i++){
            int j = 0;
            for(; j < t.length(); j++)
                if(s.charAt(i + j) != t.charAt(j))
                    break;
            if(j == t.length())
                return i;
        }
        return -1;
    }

    public static int rabinKarp(String s, String t){

        if(s.length() < t.length()) return -1;
        if(t.length() == 0) return 0;
        long tHash = 0, MOD = (long) (1e9 + 7), B = 256;
        for(int i=0; i<t.length(); i++)
            tHash = (tHash * B + t.charAt(i)) % MOD;
        long hash = 0, P = 1;
        for(int i=0; i<t.length() - 1; i++){
            hash = (hash * B + s.charAt(i)) % MOD;
            P = P * B % MOD;
        }
        for(int i=t.length()-1; i<s.length(); i++){
            hash = (hash * B + s.charAt(i)) % MOD;
            if(hash == tHash && equal(s, i - t.length() + 1, t))
                return i - t.length() + 1;
            hash = (hash - s.charAt(i - t.length() + 1) * P % MOD + MOD) % MOD;
        }
        return -1;
    }

    private static boolean equal(String s, int l, String t){
        for(int i=0; i<t.length(); i++)
            if(s.charAt(i + l) != t.charAt(i))
                return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "hello, this is liuyibobobo.";
        String t = "bo";
        SubstringMatchHelper.matchTest("bruteforce", s, t);
        SubstringMatchHelper.matchTest("rabinKarp", s, t);

        // Worst for burteforce
        int n = 1000000, m = 10000;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append('a');
        StringBuilder tb = new StringBuilder();
        for(int i=0; i<m-1; i++)
            tb.append('a');
        tb.append('b');
        SubstringMatchHelper.matchTest("bruteforce", sb.toString(), tb.toString());
        SubstringMatchHelper.matchTest("rabinKarp", sb.toString(), tb.toString());
    }
}
