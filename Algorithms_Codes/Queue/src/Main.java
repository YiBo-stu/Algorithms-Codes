import java.util.Random;

public class Main {

    // 测试使用q进行opCount个enqueue和dequeue操作所需要的时间，单位是秒
    // 传入参数是接口Queue，由于java的多态性质，可以传入实现了这个接口的类的对象
    private static double testQueue(Queue<Integer> queue, int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i=0; i<opCount; i++){
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        // 对于ArrayQueue出队操作O(n)复杂度，加上外层循环，整体时间复杂度是O(n^2)
        // 对于LoopQueue出队操作O(1)复杂度，加上外层循环，整体时间复杂度是O(n)
        for(int i=0; i<opCount; i++){
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1.0e9;
    }

    public static void main(String[] args) {

        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopArrayQueue, time: " + time2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time3 + " s");
    }
}
