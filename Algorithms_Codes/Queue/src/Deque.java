public class Deque<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public Deque(int capacity){
        data = (E[])new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public void addFront(E e){
        if(size == getCapacity()){
            resize(2 * getCapacity());
        }
        front = front==0? data.length-1 : front-1;
        data[front] = e;
        size ++;
    }

    public void addLast(E e){
        if(size == getCapacity()){
            resize(2 * getCapacity());
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    public E removeFirst(){
        E t = data[front];
        data[front] = null;
        front = (front+1)%getCapacity();
        size --;
        if(size <= getCapacity()/4 && getCapacity() / 2 != 0){
            resize(getCapacity()/2);
        }
        return t;
    }

    public E removeLast(){
        E t = data[(tail-1) % getCapacity()];
        data[(tail-1) % getCapacity()] = null;
        tail = (tail-1)%getCapacity();
        size --;
        if(size <= getCapacity()/4 && getCapacity() /2 != 0){
            resize(getCapacity()/2);
        }
        return t;
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i=0; i<size; i++){
            newData[i] = data[(front+i)%getCapacity()];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Deque: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i=0; i<getSize(); i++){
            res.append(data[(front+i)%getCapacity()]);
            if((front+i)%getCapacity() != (tail-1)%getCapacity() && front != tail){
                    res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        for(int i=0; i<=10; i++){
            deque.addFront(i);
            System.out.println(deque);
        }
        for(int i=0; i<=10; i++){
            deque.removeLast();
            System.out.println(deque);
        }
    }
}
