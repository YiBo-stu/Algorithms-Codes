public class Array<E> {
    // 需要创建类的实例，因此是范型类。尖括号中的<E>可以任意取名

    private E[] data;
    private int size;

    public Array(int capacity){
        //不能直接new一个泛型类的数组。Object类是任意类的父类
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    // 为了实现把任意数组整理成堆，所以用这个方法构建动态数组
    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for(int i=0; i<arr.length; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed. Require index>=0 and index<=size.");
        }
        if(size == data.length)
            resize(2 * data.length);
        for(int i=size; i>index; i--){
            data[i] = data[i-1];
        }
        data[index] = e;
        size ++;
    }

    E get(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    void set(int index, E e){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    public boolean contains(E e){
        for(int i=0; i<size; i++)
            if(data[i].equals(e))
                return true;
        return false;
    }

    public int find(E e){
        for(int i=0; i<size; i++)
            if(data[i].equals(e))
                return i;
        return -1;
    }

    public E remove(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Delete failed. Index is illegal.");
        E ret = data[index];
        for(int i=index; i<size-1; i++)
            data[i] = data[i+1];
        size --;
        //这句话并不是必须的
        data[size] = null;  //loitering objects != memory leak
        // data.length/2是缩容后得到的空间，保证这个空间不能为0
        if(size <= data.length/4 && data.length/2 != 0)
            resize(data.length/2);
        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    // 从数组中删除e，只删除一个
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    public void swap(int i, int j){
        if(i<0 || i>=size || j<0 || j>=size)
            throw new IllegalArgumentException("Index is illegal.");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for(int i=0; i<size; i++){
            res.append(data[i]);
            if(i != size-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i=0; i<size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}
