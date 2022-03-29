public class SelectionSort {

    private SelectionSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        for(int i=0; i<arr.length; i++){
            int minIndex = i;
            for(int j=i; j<arr.length; j++){
                if(arr[j].compareTo(arr[minIndex]) < 0){
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
        //另一种实现
//        for(int i=arr.length-1; i>=0; i--){
//            int maxIndex = i;
//            for(int j=i; j>=0; j--){
//                if(arr[maxIndex].compareTo(arr[j]) < 0){
//                    maxIndex = j;
//                }
//            }
//            swap(arr, i, maxIndex);
//        }
    }

    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

    }
}
