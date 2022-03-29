public class Array2 <E>{
    // 自己练习

    private E[] data;
    private int size;

    Array2(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    Array2(){ this(10); }

    public int getSize(){ return size; }

    public int getCapacity(){ return data.length; }

    public boolean isEmpty(){ return size == 0; }

    public void add(int index, E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed.");
        }
        if(size == getCapacity())
            resize(2 * getCapacity());
        for(int i=size; i>index; i--){
            data[i] = data[i-1];
        }
        data[index] = e;
        size ++;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    public E remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Remove failed.");
        }
        E ret = data[index];
        for(int i=index+1; i<size; i++){
            data[i-1] = data[i];
        }
        size --;
        data[size] = null;
        if(size<=getCapacity()/4 && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void set(int index, E e){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Set failed.");
        }
        data[index] = e;
    }

    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Find failed.");
        }
        return data[index];
    }

    public int find(E e){
        for(int i=0; i<size; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E e){
        for(int i=0; i<size; i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array2: size=%d, capacity=%d\n", size, getCapacity()));
        res.append("[");
        for(int i=0; i<size; i++){
            res.append(data[i]);
            if(i < size-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }


}
