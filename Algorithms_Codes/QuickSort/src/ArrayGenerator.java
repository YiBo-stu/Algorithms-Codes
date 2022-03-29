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

    public static Integer[] generateSpecialArray(int n){
        Integer[] arr = generateOrderedArray(n);
        generateSpecialArray(arr, 0, arr.length-1);
        return arr;
    }

    private static void generateSpecialArray(Integer[] arr, int l, int r){
        if(l >= r)
            return;
        QuickSort2.sort(arr, l, r);
        int mid = l + (r-l)/2;
        swap(arr, l, mid);
        generateSpecialArray(arr, l+1, r);
    }

    private static void swap(Integer[] arr, int i, int j){
        Integer t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int n = 8;
        Integer[] arr = generateSpecialArray(n);
        for(int i: arr)
            System.out.print(i + " ");
    }
}
