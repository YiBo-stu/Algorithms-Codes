import java.util.Arrays;

public class BubbleSort {

    private BubbleSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){

        for(int i=0; i<arr.length-1; i++){
            // arr[n-i, n)已经排好序
            // 在arr[n-1-i]放上合适元素
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j].compareTo(arr[j+1]) > 0)
                    swap(arr, j, j+1);
            }
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){

        for(int i=0; i<arr.length-1; i++){
            // arr[n-i, n)已经排好序
            // 在arr[n-1-i]放上合适元素
            boolean isSwapped = false;
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j].compareTo(arr[j+1]) > 0){
                    swap(arr, j, j+1);
                    isSwapped = true;
                }
            }
            if(!isSwapped)
                break;
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] arr){

        for(int i=0; i<arr.length-1; ){
            // arr[n-i, n)已经排好序
            // 在arr[n-1-i]放上合适元素
            int lastSwappedIndex = 0;
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j].compareTo(arr[j+1]) > 0){
                    swap(arr, j, j+1);
                    lastSwappedIndex = j+1;
                }
            }
            // i的含义可以理解为后面有多少个元素已经排好序了
            i = arr.length - lastSwappedIndex;
        }
    }

    public static <E extends Comparable<E>> void sort4(E[] arr){

        // arr[0, i)是有序的
        for(int i=0; i<arr.length-1; ){
            int lastSwappedIndex = arr.length - 1;
            for(int j=arr.length-1; j>i; j--){
                if(arr[j].compareTo(arr[j-1]) < 0){
                    swap(arr, j, j-1);
                    lastSwappedIndex = j;
                }
            }
            i = lastSwappedIndex;
        }
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 10000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);

        System.out.println("Random Array:");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);
        SortingHelper.sortTest("BubbleSort4", arr4);
        System.out.println();

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        arr4 = Arrays.copyOf(arr, arr.length);
        System.out.println("Ordered Array:");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);
        SortingHelper.sortTest("BubbleSort4", arr4);
    }
}
