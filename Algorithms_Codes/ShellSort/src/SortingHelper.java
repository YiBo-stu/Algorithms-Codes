public class SortingHelper {

    private SortingHelper(){}

    public static <E extends Comparable<E>> boolean isSorted(E[] arr){
        for(int i=1; i<arr.length; i++){
            if(arr[i-1].compareTo(arr[i]) > 0){
                return false;
            }
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortname, E[] arr){

        long startTime = System.nanoTime();
        if(sortname.equals("InsertionSort")){
            InsertionSort.sort(arr);
        }else if(sortname.equals("MergeSort")){
            MergeSort.sort(arr);
        }else if(sortname.equals("QuickSort2ways")){
            QuickSort.sort2ways(arr);
        }else if(sortname.equals("QuickSort3ways")){
            QuickSort.sort3ways(arr);
        }else if(sortname.equals("HeapSort")){
            HeapSort.sort(arr);
        }else if(sortname.equals("HeapSort2")){
            HeapSort.sort2(arr);
        }else if(sortname.equals("HeapSortM")){
            HeapSort.sortM(arr);
        }else if(sortname.equals("BubbleSort")){
            BubbleSort.sort(arr);
        }else if(sortname.equals("BubbleSort2")){
            BubbleSort.sort2(arr);
        }else if(sortname.equals("BubbleSort3")){
            BubbleSort.sort3(arr);
        }else if(sortname.equals("BubbleSort4")){
            BubbleSort.sort4(arr);
        }else if(sortname.equals("ShellSort")){
            ShellSort.sort(arr);
        }else if(sortname.equals("ShellSort2")){
            ShellSort.sort2(arr);
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1.0e9;
        if(!SortingHelper.isSorted(arr))
            throw new RuntimeException(sortname + " failed");
        System.out.println(String.format("%s, n=%d : %f s", sortname, arr.length, time));
    }
}