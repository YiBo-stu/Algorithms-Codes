import java.util.Arrays;

public class MergeSort2 {
    // 自己练习用

    private MergeSort2(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort(arr, 0, arr.length-1, temp);
    }

    // 递归的宏观语义：对arr[l, r]进行排序
    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp){
        // 最基本的问题
        if(l >= r){
            return;
        }
        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);
        sort(arr, mid + 1, r, temp);
        if(arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, l, mid, r, temp);
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp){
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i = l;
        int j = mid + 1;
        for(int k=l; k<=r; k++){
            if(i > mid){
                arr[k] = temp[j]; j++;
            }else if(j > r){
                arr[k] = temp[i]; i++;
            }else if(temp[i].compareTo(temp[j]) > 0){
                arr[k] = temp[j]; j++;
            }else{
                arr[k] = temp[i]; i++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSort2", arr);
    }
}
