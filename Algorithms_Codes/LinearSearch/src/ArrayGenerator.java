public class ArrayGenerator {

    //不需要它的对象
    private ArrayGenerator(){}

    public static Integer[] generateOrderedArray(int n){

        Integer[] arr = new Integer[n];
        for(int i=0; i<arr.length; i++){
            arr[i] = i;
        }
        return arr;
    }
}
