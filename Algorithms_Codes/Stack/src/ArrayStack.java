
public class ArrayStack<E> implements Stack<E> {
    // 基于动态数组的实现创建栈，支持范型，并且实现Stack<E>接口

    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    // 只有在使用动态数组来实现栈的时候才存在容积这个概念，因此它是ArrayStack特有的方法
    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    // 由于调用的是Array类中的addLast，所以不用考虑空间不够用的问题
    public void push(E e){
        array.addLast(e);
    }

    @Override
    public E pop(){
        return array.removeLast();
    }

    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");
        for(int i=0; i<array.getSize(); i++){
            res.append(array.get(i));
            if(i != array.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        arrayStack.push(3);
        System.out.println(arrayStack);
    }
}
