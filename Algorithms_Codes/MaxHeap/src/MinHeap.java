public class MinHeap<E extends Comparable<E>> {

    Array<E> data;

    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MinHeap(){
        data = new Array<>();
    }

    public MinHeap(E[] arr){
        data = new Array<>(arr);
        for(int i=parent(arr.length-1); i>=0; i--){
            siftDown(i);
        }
    }

    public int getSize(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int k){
        if(k == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (k - 1) / 2;
    }

    private int leftChild(int k){
        return 2 * k + 1;
    }

    private int rightChild(int k){
        return 2 * k + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k){
        while(k>0 && data.get(parent(k)).compareTo(data.get(k))>0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMin(){
        if(data.isEmpty())
            throw new IllegalArgumentException("Heap is empty.");
        return data.get(0);
    }

    public E extractMin(){
        E ret = findMin();
        data.swap(0, data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){

        while(leftChild(k)<data.getSize()){
            int j = leftChild(k);
            if(j+1<data.getSize() && data.get(j+1).compareTo(data.get(j))<0)
                j ++;
            if(data.get(k).compareTo(data.get(j))<=0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    public E replace(E e){
        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
