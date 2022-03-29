public class LinkedListQueue2<E> implements Queue<E> {
    // 与LinkListQueue一样，自己当做练习

    private class Node{
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
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue2(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e, null);
            head = tail;
        }
        else {
            tail.next = new Node(e, null);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if(tail == null){
            throw new IllegalArgumentException("Empty queue");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        size --;
        if(size == 0){
            tail = null;
        }
        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Empty queue.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node cur = head;
        while(cur!=null){
            res.append(cur.e + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }
}
