public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    // front和tail分别指向队首元素和队尾元素的下一个位置
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    // 由于data.length与capacity之间有1个空间的差距，因此单独写这个方法
    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void enqueue(E e){
        if((tail + 1) % data.length == front){
            resize(2 * getCapacity());
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E t = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        // 对队列进行缩容，缩容后的容量为原来的一半，不应该等于0
        if(size <= getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity() / 2);
        }
        return t;
    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    private void resize(int newCapacity){
        // resize和toString方法中都需要遍历元素，使用了两种不同的方法
        E[] newData = (E[]) new Object[newCapacity + 1];
        for(int i=0; i<size; i++){
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i=front; i!=tail; i=(i+1)% data.length){
            res.append(data[i]);
            if((i+1)%data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
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
