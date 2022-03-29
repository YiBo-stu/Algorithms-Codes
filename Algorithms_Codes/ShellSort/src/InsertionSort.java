import java.util.Arrays;

public class InsertionSort {

    private InsertionSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        for(int i=1; i<arr.length; i++){
//            for(int j=i; j>0; j--){
//                if(arr[j-1].compareTo(arr[j]) > 0){
//                    swap(arr, j, j-1);
//                } else break;
//            }

            //一个小小的优化
            for(int j=i; j>0 && arr[j-1].compareTo(arr[j])>0; j--){
                swap(arr, j, j-1);
            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r){
        // 为了让归并排序能够使用插入排序而设立的函数
        for(int i=l; i<=r; i++){
            E t = arr[i];
            int j;
            for(j=i; j-1>=l && t.compareTo(arr[j-1])<0 ; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = t;
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        for(int i=0; i<arr.length; i++){
            E t = arr[i];
            int j;
            for(j=i; j>0 && t.compareTo(arr[j-1])<0 ; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = t;
        }
    }

    //改变循环不变量，后面的是排好序的，前面的未排序
    public static <E extends Comparable<E>> void sort3(E[] arr){
        for(int i=arr.length-1; i>=0; i--){
            E t = arr[i];
            int j;
            for(j=i; j<arr.length-1 && t.compareTo(arr[j+1])>0; j++){
                arr[j] = arr[j+1];
            }
            arr[j] = t;
        }
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for(int n: dataSize){
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);

            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("InsertionSort2", arr2);
        }
    }

}
