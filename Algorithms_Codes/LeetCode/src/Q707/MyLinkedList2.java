package Q707;

// 双向链表，有dummyHead和dummyTail两个虚拟节点，添加、删除、查找指定位置的节点根据该节点与头尾的远近确定
public class MyLinkedList2 {

    private class Node{
        public int val;
        public Node prev, next;
        public Node(int val, Node pre, Node next){
            this.val = val;
            this.prev = pre;
            this.next = next;
        }
        public Node(int val){
            this(val, null, null);
        }
    }

    private Node dummyHead = new Node(0);
    private Node dummyTail = new Node(0);
    private int size = 0;

    public MyLinkedList2() {
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int index) {
        if(index < 0 || index >= size)
            return -1;
        int revIndex = size - index - 1;
        if(index <= revIndex){
            Node node = dummyHead;
            while(index-- >= 0)
                node = node.next;
            return node.val;
        }
        else{
            Node node = dummyTail;
            while(revIndex-- >= 0)
                node = node.prev;
            return node.val;
        }
    }

    public void addAtHead(int val) {
        Node next = dummyHead.next;
        dummyHead.next = new Node(val, dummyHead, dummyHead.next);
        next.prev = dummyHead.next;
        size ++;
    }

    public void addAtTail(int val) {
        Node prev = dummyTail.prev;
        dummyTail.prev = new Node(val, dummyTail.prev, dummyTail);
        prev.next = dummyTail.prev;
        size ++;
    }

    public void addAtIndex(int index, int val) {
        if(index <= 0)
            addAtHead(val);
        else if(index <= size){
            int revIndex = size - index;
            if(index <= revIndex){
                Node node = dummyHead;
                while(index-- > 0)
                    node = node.next;
                Node next = node.next;
                node.next = new Node(val, node, node.next);
                next.prev = node.next;
            }
            else{
                Node node = dummyTail;
                while(revIndex-- > 0)
                    node = node.prev;
                Node prev = node.prev;
                node.prev = new Node(val, node.prev, node);
                prev.next = node.prev;
            }
            size ++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size)
            return;
        int revIndex = size - index - 1;
        Node node;
        if(index <= revIndex){
            node = dummyHead;
            while(index-- >= 0)
                node = node.next;
        }
        else{
            node = dummyTail;
            while(revIndex-- >= 0)
                node = node.prev;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        size --;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node node = dummyHead.next;
        while (node != dummyTail){
            res.append(node.val + " ");
            node = node.next;
        }
        res.append("size = " + size);
        return res.toString();
    }

    public static void main(String[] args) {
        MyLinkedList2 list = new MyLinkedList2();
        System.out.println(list);
        list.addAtHead(1);
        System.out.println(list);
        list.addAtTail(3);
        System.out.println(list);
        list.addAtIndex(1, 2);
        System.out.println(list);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list);
        System.out.println(list.get(1));
    }
}
