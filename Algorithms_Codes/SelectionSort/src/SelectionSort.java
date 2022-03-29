public class SelectionSort {

    private SelectionSort(){}

    // 不能简单的使用范型，要对其进行约束使得它可比较，extends用在类上表示继承某一个类，在范型中表示实现某个接口
    public static <E extends Comparable<E>> void sort(E[] arr){
        // 循环不变量：arr[i, n)未排序，arr[0, i)已排序
        for(int i=0; i<arr.length; i++){

            // 获得arr[i, n)中最小值的索引
            int minIndex = i;
            for(int j=i; j<arr.length; j++){
                // 具体如何比较，不归排序算法负责，而归类的设计者负责
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

    // sort()是静态方法，它调用的方法也必须是静态方法
    private static <E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        Student[] students = {
                new Student("Alice", 98),
                new Student("Bobo", 100),
                new Student("Charles", 66)
        };
        SelectionSort.sort(students);
        for (Student student: students)
            System.out.println(student);

        int[] dataSize = {10000, 100000};
        for(int n: dataSize){
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            // 验证排序结果是否正确
            SortingHelper.sortTest("SelectionSort", arr);
        }
    }
}
