import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRed(Node node){
        // 空节点是黑色节点
        if(node == null)
            return BLACK;
        return node.color;
    }

    // 判断二叉树是否是二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i=1; i<keys.size(); i++){
            if(keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 为了方便使用而定义的辅助函数
    // 返回以node为根节点的二分搜索树中key所在的节点
    private Node getNode(Node node, K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) == 0)
            return node;
        if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null? null: node.value;
    }

    //    node                    x
    //   /   \      左旋转        / \
    //  T1    x    ------->   node  T3
    //       / \              /  \
    //     T2   T3           T1  T2
    private Node leftRotate(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //      node                 x
    //     /    \     右旋转     / \
    //    x     T2   ------>   y  node
    //   / \                      /  \
    //  y  T1                    T1  T2
    private Node rightRotate(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;

    }

    // 向红黑树中添加新元素
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value){
        if(node == null){
            size ++;
            return new Node(key, value);
        }
        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        // 红黑树性质的维护
        if(isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if(isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if(isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + "doesn't exist.");
        node.value = newValue;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if(node == null)
            return null;
        Node retNode;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }
        else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }
            else{
                // 待删除节点左右字数都不为空的情况
                Node successor = minimum(node.right);
                // remove函数已经加上了对平衡性的处理
                // 所以用remove替换removeMin
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        return retNode;
    }
}
