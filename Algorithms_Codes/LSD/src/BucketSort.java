import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {

    private BucketSort(){}

    public static void sort(Integer[] arr, int B){

        if(B <= 1)
            throw new IllegalArgumentException("B must be greater than 1");

        Integer[] temp = new Integer[arr.length];
        sort(arr, 0, arr.length - 1, B, temp);
    }

    private static void sort(Integer[] arr, int left, int right, int B, Integer[] temp){

        if(left >= right) return;
        int minV = Integer.MAX_VALUE, maxV = Integer.MIN_VALUE;
        for(int i=left; i<=right; i++){
            minV = Math.min(minV, arr[i]);
            maxV = Math.max(maxV, arr[i]);
        }
        if(minV == maxV) return;

        // 计算每个桶的容量
        int d = (maxV - minV) / B + (((maxV - minV) % B == 0) ? 0 : 1);

        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        for(int i=left; i<=right; i++)
            cnt[(arr[i] - minV) / d] ++;

        for(int i=0; i<B; i++)
            index[i + 1] = index[i] + cnt[i];

        for(int i=left; i<=right; i++){
            int p = (arr[i] - minV) / d;
            temp[left + index[p]] = arr[i];
            index[p] ++;
        }

        for(int i=left; i<=right; i++)
            arr[i] = temp[i];

        sort(arr, left, left + index[0] - 1, B, temp);
        for(int i=1; i<B; i++){
            sort(arr, left + index[i - 1], left + index[i] - 1, B, temp);
        }
    }

    // c:指定每个桶中元素的最大取值范围是多少
    public static void sort2(Integer[] arr, int c){

        if(c <= 0)
            throw new IllegalArgumentException("c must be greater than 0");

        int B = arr.length / c + (arr.length % c == 0 ? 0 : 1);

        LinkedList<Integer>[] buckets = new LinkedList[B];
        for(int i=0; i<B; i++)
            buckets[i] = new LinkedList<>();

        int minV = Integer.MAX_VALUE, maxV = Integer.MIN_VALUE;
        for(int e: arr){
            minV = Math.min(minV, e);
            maxV = Math.max(maxV, e);
        }

        for(int e: arr)
            buckets[(e - minV) / c].add(e);

        for(int i=0; i<B; i++)
            Collections.sort(buckets[i]);

        int index = 0;
        for(int i=0; i<B; i++)
            for(int e: buckets[i])
                arr[index ++] = e;
    }

    public static void main(String[] args) {

        int n = 10000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("BucketSort", arr);
        SortingHelper.sortTest("BucketSort2", arr2);
    }
}
