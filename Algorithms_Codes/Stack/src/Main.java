import java.util.Random;

public class Main {

    private static double testStack(Stack<Integer> stack, int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i=0; i<opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i=0; i<opCount; i++)
            stack.pop();
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1.0e9;
    }

    public static void main(String[] args) {
//        ArrayStack<Integer> stack = new ArrayStack<>();
//        for(int i=0; i<5; i++){
//            stack.push(i);
//            System.out.println(stack);
//        }
//        stack.pop();
//        System.out.println(stack);
        //LinkListStack包含更多的new操作
        int opCount = 1000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + "s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + "s");
    }
}
