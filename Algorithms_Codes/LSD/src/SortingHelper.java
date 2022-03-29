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
        if(sortname.equals("BucketSort")){
            Integer[] intArr = (Integer[]) arr;
            BucketSort.sort(intArr, 200);
        }
        else if(sortname.equals("BucketSort2")){
            Integer[] intArr = (Integer[]) arr;
            BucketSort.sort2(intArr, 100);
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1.0e9;
        if(!SortingHelper.isSorted(arr))
            throw new RuntimeException(sortname + " failed");
        System.out.println(String.format("%s, n=%d : %f s", sortname, arr.length, time));
    }
}
