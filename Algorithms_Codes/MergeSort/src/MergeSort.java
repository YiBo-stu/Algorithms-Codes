import java.util.Arrays;

public class MergeSort {

    // 排序算法不需要创建类的对象就可以调用
    private MergeSort(){}

    // 自顶向下的归并排序
    public static <E extends Comparable<E>> void sort(E[] arr){
        // 优化3：开辟空间的操作放到这里，整个排序过程只开辟一次空间，这种优化是稳定的
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort(arr, 0, arr.length-1, temp);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, E[] temp){
        // 优化2：数组规模小时用插入排序替代归并排序，这种优化是不稳定的，这里没有使用
        if(l >= r) return;
        int mid = l + (r - l) / 2;
        sort(arr, l, mid, temp);
        sort(arr, mid+1, r, temp);
        // 优化1：如果数组已经有序，就不再执行合并过程
        if(arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r, temp);
    }

    // 自底向上的归并排序，非递归算法
    public static <E extends Comparable<E>> void sortBU(E[] arr){

        E[] temp = Arrays.copyOf(arr, arr.length);
        // 一个小优化，要排序的数组长度小时用插入排序法排序
        for(int i=0; i<arr.length; i+=16){
            InsertionSort.sort(arr, i, Math.min(i+15, arr.length-1));
        }

        // 遍历合并的区间的长度
        for(int size=16; size<arr.length; size*=2){
            // 遍历合并的区间的起始位置
            // 合并[i, i+size-1]和[i+size, i+size+size-1]
            for(int i=0; i+size<arr.length; i+=size*2){
                if(arr[i+size-1].compareTo(arr[i+size]) > 0)
                    merge(arr, i, i+size-1, Math.min(i+size+size-1, arr.length-1), temp);
            }
        }
    }

    // 合并两个有序区间arr[l, mid] 和 arr[mid+1, r]
    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, E[] temp){
        // 使用这种方式，arr与temp的相应位置不再有偏移量
        System.arraycopy(arr, l, temp, l, r - l + 1);
        int i=l, j=mid+1;
        for(int k=l; k<=r; k++){
            if(i > mid){
                arr[k] = temp[j]; j ++;
            }else if(j > r){
                arr[k] = temp[i]; i ++;
            }else if(temp[i].compareTo(temp[j]) <= 0){
                arr[k] = temp[i]; i ++;
            }else {
                arr[k] = temp[j]; j ++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("SelectionSort", arr);
        SortingHelper.sortTest("InsertionSort", arr2);
        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("MergeSortBU", arr2);

    }
}
