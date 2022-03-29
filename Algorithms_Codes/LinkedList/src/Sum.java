public class Sum {
    // 为了理解递归而写的简单的求和函数
    public static int sum(int[] arr){
        return sum(arr, 0);
    }

    // 计算区间[l, n)的数字和
    private static int sum(int[] arr, int l){
        if(l == arr.length)
            return 0;
        return arr[l] + sum(arr, l+1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Sum.sum(nums));
    }
}
