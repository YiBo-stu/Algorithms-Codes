
public class LinkedList2<E> {
    // 以递归的形式实现链表的增删改查

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

    // 递归实现不需要使用虚拟头节点
    private Node head;
    private int size;

    public LinkedList2(){
        // 与带有虚拟头节点的链表不同，头节点就是空
        head = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int index, E e){
        // 这里需要注意把private add的结果赋值给head
        head = add(head, index, e);
        size ++;
    }

    private Node add(Node node, int index, E e){
        if(index == 0)
            // 新节点的next指向node
            return new Node(e, node);
        node.next = add(node.next, index-1, e);
        return node;
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void addLast(E e){
        add(size, e);
    }

    public E get(int index){
        return get(head, index);
    }

    private E get(Node node, int index){
        if(index == 0){
            return node.e;
        }
        return get(node.next, index-1);
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e){
        set(head, index, e);
    }

    private void set(Node node, int index, E e){
        if(index == 0){
            node.e = e;
        }
        set(node.next, index-1, e);
    }

    public boolean contains(E e){
        return contains(head, e);
    }

    private boolean contains(Node node, E e){
        if(node == null)
            return false;
        if(node.e.equals(e))
            return true;
        return contains(node.next, e);
    }

    public void remove(int index){
        // 这里需要注意把private remove的结果赋值给head
        head = remove(head, index);
        size --;
    }

    private Node remove(Node node, int index){
        if(index == 0){
            return node.next;
        }
        node.next = remove(node.next, index-1);
        return node;
    }

    public void removeFirst(){
        remove(0);
    }

    public void removeLast(){
        remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for(Node cur=head; cur!=null; cur=cur.next){
            res.append(cur.e + "->");
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList2<Integer> linkedList2 = new LinkedList2<>();
        for(int i=0; i<5; i++){
            linkedList2.addFirst(i);
            System.out.println(linkedList2);
        }
        linkedList2.add(2, 666);
        System.out.println(linkedList2);
        linkedList2.remove(2);
        System.out.println(linkedList2);
        linkedList2.removeFirst();
        System.out.println(linkedList2);
        linkedList2.removeLast();
        System.out.println(linkedList2);
        System.out.println(linkedList2.contains(3));
        System.out.println(linkedList2.contains(4));
    }
}
