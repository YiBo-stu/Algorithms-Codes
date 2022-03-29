package Q622;

public class MyCircularQueue {

    private int[] data;
    private int front, tail;
    private int size;

    public MyCircularQueue(int k) {

        data = new int[k];
        front = 0;
        tail = 0;
        size = 0;
    }

    public boolean enQueue(int value) {

        if(size == data.length)
            return false;
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size ++;
        return true;
    }

    public boolean deQueue() {

        if(size == 0)
            return false;
        front = (front + 1) % data.length;
        size --;
        return true;
    }

    public int Front() {

        if(size == 0)
            return -1;
        return data[front];
    }

    public int Rear() {

        if(size == 0)
            return -1;
        return data[(tail + data.length - 1) % data.length];
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public boolean isFull() {

        return size == data.length;
    }
}
