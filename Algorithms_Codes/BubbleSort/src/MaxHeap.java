import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    // 使用Heapify以构造函数的形式将任意数组整理成堆
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for(int i=parent(arr.length-1); i>=0; i--)
            siftDown(i);
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){
        while(k>0 && data.get(k).compareTo(data.get(parent(k)))>0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 查看堆中最大元素
    public E findMax(){
        if(data.isEmpty())
            throw new IllegalArgumentException("Heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){
        // 进入循环的条件为左孩子存在
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if(j+1<data.getSize() && data.get(j).compareTo(data.get(j+1))<0)
                j = rightChild(k);
            if(data.get(k).compareTo(data.get(j))>=0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中的最大元素，替换成e
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {
        int n = 100000;
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random rnd = new Random();
        for(int i=0; i<n; i++)
            heap.add(rnd.nextInt(n));

        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.extractMax();
        }

        for(int i=0; i<arr.length-1; i++)
            if(arr[i] < arr[i+1])
                throw new RuntimeException("Heap failed");
        System.out.println("Heap correct.");
    }
}
