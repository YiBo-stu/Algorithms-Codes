import java.util.Arrays;

public class ShellSort {

    private ShellSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        // h是同一组元素相邻两个的间隔
        int h = arr.length / 2;
        while(h >= 1){
            for(int i=h; i<arr.length; i++){
                E t = arr[i];
                int j;
                for(j=i; j-h>=0 && t.compareTo(arr[j-h])<0; j-=h){
                    arr[j] = arr[j-h];
                }
                arr[j] = t;
            }
            h /= 2;
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        // h是同一组元素相邻两个的间隔
        int h = 1;
        while(h < arr.length) h = 3 * h + 1;
        while(h >= 1){
            for(int i=h; i<arr.length; i++){
                E t = arr[i];
                int j;
                for(j=i; j-h>=0 && t.compareTo(arr[j-h])<0; j-=h){
                    arr[j] = arr[j-h];
                }
                arr[j] = t;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("ShellSort", arr);
        SortingHelper.sortTest("ShellSort2", arr2);
        SortingHelper.sortTest("MergeSort", arr3);
        SortingHelper.sortTest("QuickSort2ways", arr4);
        SortingHelper.sortTest("InsertionSort", arr5);
    }
}
