import java.util.ArrayList;

public class AVLTree2<K extends Comparable<K>, V> {
    // 自己练习用
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree2(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    public void add(K key, V value){
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if(node == null){
            size ++;
            return new Node(key, value);
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
//            return node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
//            return node;
        }
        else {
            node.value = value;
            return node;
        }
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) > 0){
            return rightRotate(node);
        }
        // RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) < 0){
            return leftRotate(node);
        }
        // LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node getNode(Node node, K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) == 0)
            return node;
        if(key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }
        return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public void set(K key, V value){
        if(contains(key)){
            getNode(root, key).value = value;
            return;
        }
        throw new IllegalArgumentException("key doesn't exist.");
    }

    public V get(K key){
        Node node = getNode(root, key);
        return node == null? null: node.value;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public V remove(K key){
        V ret = get(key);
        root = remove(root, key);
        return ret;
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
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        if(retNode == null)
            return null;
        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) > 0){
            return rightRotate(retNode);
        }
        // RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) < 0){
            return leftRotate(retNode);
        }
        // LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if(node == null)
            return true;
        if(Math.abs(getBalanceFactor(node)) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    public boolean isBST(){
        ArrayList<K> arrayList = new ArrayList<>();
        inOrder(root, arrayList);
        for(int i=1; i<arrayList.size(); i++){
            if(arrayList.get(i).compareTo(arrayList.get(i-1)) < 0)
                return false;
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> arrayList){
        if(node == null)
            return;
        inOrder(node.left, arrayList);
        arrayList.add(node.key);
        inOrder(node.right, arrayList);
    }
}
