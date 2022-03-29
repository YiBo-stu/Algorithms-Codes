import java.util.Random;

public class ArrayGenerator {

    private ArrayGenerator(){}

    public static Integer[] generateOrderedArray(int n){

        Integer[] arr = new Integer[n];
        for(int i=0; i<arr.length; i++){
            arr[i] = i;
        }
        return arr;
    }

    public static Integer[] generateRandomArray(int n, int bound){
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for(int i=0; i<arr.length; i++){
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }

    private static void swap(Integer[] arr, int i, int j){
        Integer t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
