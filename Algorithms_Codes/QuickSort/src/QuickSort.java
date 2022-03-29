import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private QuickSort(){}

    // 带有随机化的单路快速排序
    public static <E extends Comparable<E>> void sort(E[] arr){
        Random rnd = new Random();
        sort(arr, 0, arr.length-1, rnd);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random rnd){
        if(l >= r) return;
        int p = partition(arr, l, r, rnd);
        sort(arr, l, p-1, rnd);
        sort(arr, p+1, r, rnd);
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r, Random rnd){
        // 对基础的快速排序法进行修正
        // 生成[l, r]区间的随机数
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

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

    // 双路快速排序
    public static <E extends Comparable<E>> void sort2ways(E[] arr){
        Random rnd = new Random();
        sort2ways(arr, 0, arr.length-1, rnd);
    }

    private static <E extends Comparable<E>> void sort2ways(E[] arr, int l, int r, Random rnd){
        if(l >= r)
            return;
        int p = partition2(arr, l, r, rnd);
        sort2ways(arr, l, p-1, rnd);
        sort2ways(arr, p+1, r, rnd);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr, int l, int r, Random rnd){
        // 在区间[l, r]中随机选择一个标定点
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1, i-1] <= v, arr[j+1, r] >= v
        int i = l+1, j = r;
        while(true){
            // 注意这里i==j的情况下也可以继续行动，因为此时这个元素还没有访问
            while(i <= j && arr[i].compareTo(arr[l]) < 0)
                i ++;
            while(j >= i && arr[j].compareTo(arr[l]) > 0)
                j --;
            // 此时i==j依然成立的话，这个元素已经被访问过了，且等于标定点的值
            if(i >= j) break;
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }

    // 三路快速排序
    public static <E extends Comparable<E>> void sort3ways(E[] arr){
        Random rnd = new Random();
        sort3ways(arr, 0, arr.length-1, rnd);
    }

    private static <E extends Comparable<E>> void sort3ways(E[] arr, int l, int r, Random rnd){
        if(l >= r)
            return;
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);
        // arr[l+1, lt] < v, arr[lt+1, gt-1] = v, arr[gt, r] > v
        int lt = l, gt = r+1, i = l+1;
        // 因为i不是每次都加1，所以用while循环
        while(i < gt){
            if(arr[i].compareTo(arr[l]) < 0){
                lt ++;
                swap(arr, lt, i);
                i ++;
            }
            else if(arr[i].compareTo(arr[l]) > 0){
                gt --;
                swap(arr, i, gt);
            }
            else
                i ++;
        }
        swap(arr, l, lt);
        sort3ways(arr, l, lt-1, rnd);
        sort3ways(arr, gt, r, rnd);
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 10000;

        System.out.println("Random Array:");
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort", arr2);
        SortingHelper.sortTest("QuickSort2ways", arr3);
        SortingHelper.sortTest("QuickSort3ways", arr4);
        System.out.println();

        System.out.println("Ordered Array:");
        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        arr4 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort", arr2);
        SortingHelper.sortTest("QuickSort2ways", arr3);
        SortingHelper.sortTest("QuickSort3ways", arr4);
        System.out.println();

        System.out.println("Same Value Array:");
        arr = ArrayGenerator.generateRandomArray(n, 1);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        arr4 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort", arr2);
        SortingHelper.sortTest("QuickSort2ways", arr3);
        SortingHelper.sortTest("QuickSort3ways", arr4);

    }
}
