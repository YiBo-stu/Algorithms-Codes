
// 这里实现的是不浪费一个空间的LoopQueue
public class LoopQueue2<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue2(int capacity){
        data = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue2(){
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    public int getCapacity(){
        return data.length;
    }

    @Override
    public void enqueue(E e) {
        if(size == getCapacity()){
            resize(2 * getCapacity());
        }
        data[tail] = e;
        tail = (tail + 1) % getCapacity();
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Can't dequeue from empty queue.");
        E t = data[front];
        data[front] = null;
        front = (front + 1) % getCapacity();
        size--;
        if(size <= getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return t;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for(int i=0; i<size; i++){
            newData[i] = data[(i + front) % getCapacity()];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size=%d, capacity=%d\n", size, getCapacity()));
        res.append("front [");
        for(int i=0; i<size; i++){
            res.append(data[(i + front) % getCapacity()]);
            if(i != size-1)
                res.append(",");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue2<Integer> queue = new LoopQueue2<>();
        for(int i=0; i<10; i++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i%3 ==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
