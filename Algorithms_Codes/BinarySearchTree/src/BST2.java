import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BST2<E extends Comparable<E>> {
    // 自己练习用
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public BST2(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root, e);
    }

    private Node add(Node node, E e){
        if(node == null){
            size ++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        }
        else if(e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }

    public void addNR(E e){
        if(root == null){
            size ++;
            root = new Node(e);
            return;
        }
        Node prev = root;
        while(true){
            if(e.compareTo(prev.e) == 0)
                return;
            if(e.compareTo(prev.e) < 0){
                if(prev.left == null){
                    prev.left = new Node(e);
                    size ++;
                    return;
                }
                prev = prev.left;
            }
            if(e.compareTo(prev.e) > 0){
                if(prev.right == null){
                    prev.right = new Node(e);
                    size ++;
                    return;
                }
                prev = prev.right;
            }
        }
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if(node == null)
            return false;
        if(e.compareTo(node.e) == 0)
            return true;
        if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        return contains(node.right, e);
    }

    public boolean containsNR(E e){
        if(root == null)
            return false;
        Node prev = root;
        while(true){
            if(e.compareTo(prev.e) == 0)
                return true;
            if(e.compareTo(prev.e) < 0){
                if(prev.left == null)
                    return false;
                prev = prev.left;
            }
            else if(e.compareTo(prev.e) > 0){
                if(prev.right == null)
                    return false;
                prev = prev.right;
            }
        }
    }

    public E minimum(){
        if(root == null){
            throw new IllegalArgumentException("BST2 is empty.");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public E minimumNR(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        Node cur = root;
        while(true){
            if(cur.left == null)
                return cur.e;
            cur = cur.left;
        }
    }

    public E maximum(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null)
            return node;
        return maximum(node.right);
    }

    public E maximumNR(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        Node cur = root;
        while(true){
            if(cur.right == null)
                return cur.e;
            cur = cur.right;
        }
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }
        else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            else{
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    public void preOrder(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.e);
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
    }

    public void inOrder(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder(){
        if(root == null)
            throw new IllegalArgumentException("BST2 is empty.");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node.e);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }
}
