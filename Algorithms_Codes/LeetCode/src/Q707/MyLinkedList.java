package Q707;

public class MyLinkedList {

    private class Node {
        public int val;
        public Node next;
        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
        public Node(int val){this(val, null);}
    }

    private Node dummyHead = new Node(0);
    private int size = 0;

    public MyLinkedList() {

    }

    public int get(int index) {
        if(index < 0 || index >= size)
            return -1;
        Node node = dummyHead;
        while(index-- >= 0){
            node = node.next;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        dummyHead.next = new Node(val, dummyHead.next);
        size ++;
    }

    public void addAtTail(int val) {
        Node pre = dummyHead;
        while(pre.next != null)
            pre = pre.next;
        pre.next = new Node(val);
        size ++;
    }

    public void addAtIndex(int index, int val) {
        if(index <= 0)
            addAtHead(val);
        else if(index <= size){
            Node pre = dummyHead;
            while (index-- > 0)
                pre = pre.next;
            pre.next = new Node(val, pre.next);
            size ++;
        }
    }

    public void deleteAtIndex(int index) {
        if(index >=0 && index < size){
            Node pre = dummyHead;
            while(index-- > 0)
                pre = pre.next;
            pre.next = pre.next.next;
            size --;
        }
    }
}
