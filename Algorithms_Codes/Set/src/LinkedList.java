public class LinkedList<E> {

    // 节点设计成链表这个类的内部类，设计为私有，只有在链表这个数据结构内可以访问Node
    private class Node{
        // 设计成公有，在LinkedList这个结构中可以随意访问和修改，不需要专门的get()和set()
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    // 为链表设计的虚拟头结点，链表的第一个元素是dummyHead.next对应节点的元素。这对用户是屏蔽的
    private Node dummyHead;
    private int size;

    public LinkedList(){
        // 链表初始存在一个唯一的虚拟头节点
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表的index位置添加新的元素e，这在实际中是一个不常用的操作
    public void add(int index, E e){
        if(index<0 || index>size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node prev = dummyHead;
        for(int i=0; i<index; i++){
            prev = prev.next;
        }
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next = new Node(e, prev.next);
        size ++;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    // 不常用，主要是练习
    public E get(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for(int i=0; i<index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    // 不常用，练习
    public void set(int index, E e){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for(int i=0; i<index; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
//        for(int i=0; i<size; i++){
//            if(cur.e.equals(e))
//                return true;
//            cur = cur.next;
//        }
        while(cur != null){
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for(int i=0; i<index; i++){
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public void removeElement(E e){
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for(Node cur=dummyHead.next; cur!=null; cur=cur.next){
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }
}
