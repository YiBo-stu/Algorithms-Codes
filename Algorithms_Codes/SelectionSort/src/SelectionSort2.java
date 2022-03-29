public class SelectionSort2 {
    // 自己练习

    private SelectionSort2(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        // [0, i), [i, n)
        for(int i=0; i<arr.length; i++){

            int minIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j].compareTo(arr[minIndex]) < 0){
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("SelectionSort", arr);
    }
}
