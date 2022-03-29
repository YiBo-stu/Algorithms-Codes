
public class LinearSearch {
    // 不是使用类的对象，而是使用类中的方法，把类定义为范型类是没有意义的

    // 把构造函数声明成私有的，阻止用户创建类的对象
    private LinearSearch(){}

    public static <E> int search(E[] data, E target){
        // 泛型方法
        for(int i=0; i<data.length; i++){
            // 判断相等不应该用==，==判断的是引用相等，equals()默认比较的是类对象的地址
            if(data[i].equals(target)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 范型只能接受类对象，不能接受基本数据类型:boolean/byte/char/short/int/long/float/double
        // 每个基本数据类型都有对应的包装类:Boolean/Byte/Character/Short/Integer/Long/Float/Double
        Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        int res = LinearSearch.search(data, 16);
        System.out.println(res);

        Student[] students = {new Student("Alice"), new Student("Bobo"), new Student("Charles")};
        Student bobo = new Student("bobo");
        int res2 = LinearSearch.search(students, bobo);
        System.out.println(res2);

        int[] dataSize = {1000000, 10000000};
        for(int n: dataSize){
            Integer[] bigdata = ArrayGenerator.generateOrderedArray(n);
            // 返回一个长整型的时间戳，当前以纳秒来计算的时间戳
            long startTime = System.nanoTime();
            for(int i=0; i<100; i++)
                LinearSearch.search(bigdata, n);
            long endTime = System.nanoTime();
            // 纳秒，微秒，毫秒，秒
            double time = (endTime - startTime) / 1.0e9;
            System.out.println("n=" + n + ", 100 runs : " + time + " s");
        }

    }
}
