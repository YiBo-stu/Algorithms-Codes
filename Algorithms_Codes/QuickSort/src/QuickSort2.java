import java.util.Arrays;
import java.util.Random;

public class QuickSort2 {
    // 为了完成作业2而编写的类
    // 作业2：每次选择中间的元素作为标定点，对于这样的实现，设计一个ArrayGenerator.generateSpecialArray(n)方法生成一个数组，
    // 使得QuickSort的时间复杂度为O(n^2)


    private QuickSort2(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        sort(arr, 0, arr.length-1);
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r){
        if(l >= r)
            return;
        int p = partition(arr, l, r);
        sort(arr, l, p-1);
        sort(arr, p+1, r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r){
        swap(arr, l, l+(r-l)/2);

        int j = l;
        // arr[l+1, j]<v, arr[j+1, i-1]>=v
        for(int i=l+1; i<=r; i++){
            if(arr[i].compareTo(arr[l]) < 0){
                j ++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
//        Integer[] arr = ArrayGenerator.generateOrderedArray(n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort", arr2);
    }
}
