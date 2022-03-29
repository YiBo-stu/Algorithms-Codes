import java.util.Arrays;

public class LSDSort {

    private LSDSort(){}

    // int W代表等长字符串的长度，在一定程度上提醒用户传进来的字符串需要是等长的
    public static void sort(String[] arr, int W){

        for(String s: arr)
            if(s.length() != W)
                throw new IllegalArgumentException("All Strings' length must be equal");

        // 把循环中要反复使用的变量及数组放到循环外面以提升性能
        int R = 256;
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];

        // O(W * (n + R)) => O(n)
        for(int r=W-1; r>=0; r--){

            // 对第r位进行计数排序
            Arrays.fill(cnt, 0);
            for(String s: arr)
                cnt[s.charAt(r)] ++;

            for(int i=0; i<R; i++)
                index[i + 1] = index[i] + cnt[i];

            for(String s: arr)
                temp[index[s.charAt(r)] ++] = s;

            for(int i=0; i<arr.length; i++)
                arr[i] = temp[i];
        }
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for(String s: arr)
            System.out.println(s);
    }
}
