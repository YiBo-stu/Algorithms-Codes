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
            // 随机数范围[0, bound)
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }
}
