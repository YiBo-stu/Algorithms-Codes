import java.util.Arrays;

public class HeapSort {

    private HeapSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        MaxHeap<E> heap = new MaxHeap<>();
        for(E e: arr)
            heap.add(e);
        for(int i=arr.length-1; i>=0; i--)
            arr[i] = heap.extractMax();
    }

    public static <E extends Comparable<E>> void sortM(E[] arr){
        MinHeap<E> heap = new MinHeap<>();
        for(E e: arr)
            heap.add(e);
        for(int i=0; i<arr.length; i++)
            arr[i] = heap.extractMin();
    }

    public static <E extends Comparable<E>> void sort2(E[] arr){
        if(arr.length <= 1) return;
        // 先将数组arr用heapify整理成堆
        for(int i=(arr.length-2)/2; i>=0; i--){
            siftDown(arr, i, arr.length);
        }
        // 再把最大元素与当前堆的最后一个元素交换后执行siftDown
        for(int i=arr.length-1; i>0; i--){
            swap(arr, 0, i);
            siftDown(arr, 0, i);
        }
    }

    // 对arr[0, n)所形成的最大堆中索引为k的元素执行siftDown
    private static <E extends Comparable<E>> void siftDown(E[] arr, int k, int n){
        // 进入循环的条件为左孩子存在
        while(2 * k + 1 < n){
            int j = 2 * k + 1;
            if(j+1<n && arr[j].compareTo(arr[j+1])<0)
                j ++;
            if(arr[k].compareTo(arr[j])>=0)
                break;
            swap(arr, k, j);
            k = j;
        }
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);
        Integer[] arr6 = Arrays.copyOf(arr, arr.length);


        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort2ways", arr2);
        SortingHelper.sortTest("QuickSort3ways", arr3);
        SortingHelper.sortTest("HeapSort", arr4);
        SortingHelper.sortTest("HeapSort2", arr5);
        SortingHelper.sortTest("HeapSortM", arr6);

    }
}
