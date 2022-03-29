public class SortingHelper {

    private SortingHelper(){}

    // 由于要判断数组是否有序，所以它必须是可比较的，必须实现Comparable接口
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
        // 这里可以使用更高级的java反射机制实现
        if(sortname.equals("SelectionSort")){
            SelectionSort.sort(arr);
        }else if(sortname.equals("InsertionSort")){
            InsertionSort.sort(arr);
        }else if(sortname.equals("InsertionSort2")){
            InsertionSort.sort2(arr);
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1.0e9;
        if(!SortingHelper.isSorted(arr))
            throw new RuntimeException(sortname + " failed");
        System.out.println(String.format("%s, n=%d : %f s", sortname, arr.length, time));
    }
}
