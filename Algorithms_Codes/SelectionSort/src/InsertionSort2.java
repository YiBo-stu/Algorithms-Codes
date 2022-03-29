public class InsertionSort2 {
    // 自己练习

    private InsertionSort2(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        // [0, i), [i, n)
        for(int i=0; i<arr.length; i++){
            E t = arr[i];
            int j;
            for(j=i; j>0 && t.compareTo(arr[j-1]) < 0; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = t;
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("InsertionSort", arr);
    }
}
