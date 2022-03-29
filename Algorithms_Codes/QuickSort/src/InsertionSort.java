import java.util.Arrays;

public class InsertionSort {

    private InsertionSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        for(int i=0; i<arr.length; i++){
            // 将arr[i]插入到合适的位置
            for(int j=i; j>0; j--){
                // if成立的条件也即循环继续执行的条件，可以进行改进
                if(arr[j-1].compareTo(arr[j]) > 0){
                    swap(arr, j, j-1);
                } else break;
            }

            //一个小小的优化，把循环继续执行的两个条件放到一起
            for(int j=i; j>0 && arr[j-1].compareTo(arr[j])>0; j--){
                swap(arr, j, j-1);
            }
        }
    }

    // 为了优化QuickSort而设立的接口
    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r){
        for(int i=l; i<=r; i++){
            // 将arr[i]插入到合适位置
            E t = arr[i];
            int j;
            for(j=i; j>l && t.compareTo(arr[j-1])<0 ; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = t;
        }
    }

    // 插入排序的小优化，并不是时间复杂度上的优化，用赋值取代交换以节省时间
    public static <E extends Comparable<E>> void sort2(E[] arr){
        for(int i=0; i<arr.length; i++){
            // 将arr[i]插入到合适位置
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
