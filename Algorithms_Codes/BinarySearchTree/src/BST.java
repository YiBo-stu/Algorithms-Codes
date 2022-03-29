import java.util.*;

// 往BST中添加元素时需要进行比较，因此要求实现Comparable接口
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 为了模拟递归调用过程中的系统栈而设置的类
    private class Command{
        String s; // go, print
        Node node;
        Command(String s, Node node){
            this.s = s;
            this.node = node;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){ return size; }

    public boolean isEmpty(){ return size == 0; }

    // 递归实现
    public void add(E e){
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，返回插入新节点后二分搜索树的根
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

    // 非递归实现
    public void addNR(E e){
        if(root == null){
            root = new Node(e);
            size ++;
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
            else{
                if(prev.right == null){
                    prev.right = new Node(e);
                    size ++;
                    return;
                }
                prev = prev.right;
            }
        }
    }

    // 查询元素
    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if(node == null)
            return false;
        if(e.compareTo(node.e) == 0){
            return true;
        }
        if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 前序遍历的递归实现
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null)
            return;
        // 这里的遍历只是对元素进行打印
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //前序遍历的非递归实现
    public void preOrderNR(){
        if(root == null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    public void preOrderNR2(){
        if(root == null)
            return;
        // 为了模拟系统栈，Stack里存放的是命令
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while(!stack.isEmpty()){
            Command command = stack.pop();
            if(command.s.equals("print"))
                System.out.println(command.node.e);
            else{
                assert (command.s.equals("go"));
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right));
                if(command.node.left != null)
                    stack.push(new Command("go", command.node.left));
                stack.push(new Command("print", command.node));
            }
        }
    }

    // 中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 中序遍历的非递归实现
    public void inOrderNR(){
        if(root == null)
            return;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            Node node = stack.pop();
            System.out.println(node.e);
            if(node.right != null)
                cur = node.right;
        }
    }

    public void inOrderNR2(){
        if(root == null)
            return;
        // 为了模拟系统栈，Stack里存放的是命令
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while(!stack.isEmpty()){
            Command command = stack.pop();
            if(command.s.equals("print"))
                System.out.println(command.node.e);
            else{
                assert (command.s.equals("go"));
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right));
                stack.push(new Command("print", command.node));
                if(command.node.left != null)
                    stack.push(new Command("go", command.node.left));
            }
        }
    }

    // 后序遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 后序遍历的非递归实现
    public void postOrderNR(){
        if(root == null)
            return;
        Stack<Node> stack = new Stack<>();
        Stack<Node> res = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            res.push(cur);
            if(cur.left != null)
                stack.push(cur.left);
            if(cur.right != null)
                stack.push(cur.right);
        }
        while(!res.isEmpty()){
            System.out.println(res.pop().e);
        }
    }

    public void postOrderNR2(){
        if(root == null)
            return;
        // 为了模拟系统栈，Stack里存放的是命令
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while(!stack.isEmpty()){
            Command command = stack.pop();
            if(command.s.equals("print"))
                System.out.println(command.node.e);
            else{
                stack.push(new Command("print", command.node));
                assert (command.s.equals("go"));
                if(command.node.right != null)
                    stack.push(new Command("go", command.node.right));
                if(command.node.left != null)
                    stack.push(new Command("go", command.node.left));
            }
        }
    }

    // 层序遍历，又叫广度优先遍历
    public void leverOrder(){
        // 层序遍历需要借助队列来实现
        // java.util.Queue是一个接口，实现时要选择一个具体的底层数据结构
        // 这里有个小bug，当树为空时会报错
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    // 寻找二分搜索树中的最小值
    public E minimum(){
        if(root == null)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 寻找二分搜索树中最小值的非递归实现
    public E minimum2(){
        if(root == null)
            throw new IllegalArgumentException("BST is empty");
        Node cur = root;
        while(cur.left != null){
            cur = cur.left;
        }
        return cur.e;
    }

    // 寻找二分搜索树中的最大值
    public E maximum(){
        if(root == null)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null)
            return node;
        return maximum(node.right);
    }

    public E maximum2(){
        if(root == null)
            throw new IllegalArgumentException("BST is empty");
        Node cur = root;
        while(cur.right != null)
            cur = cur.right;
        return cur.e;
    }

    // 删除二分搜索树中最小值所在的节点，返回最小值
    public E removeMin(){
        E e = minimum();
        root = removeMin(root);
        return e;
    }

    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后的二分搜索树的根
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

    // 删除二分搜索树中最大值所在的节点，返回最大值
    public E removeMax(){
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    // 删除以node为根的二分搜索树中的最大节点
    // 返回删除节点后的二分搜索树的根
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

    // 删除以node为根的二分搜索树中值为e的节点
    // 返回删除节点后的新的二分搜索树的根
    private Node remove(Node node, E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        } else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        } else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // node.left != null, node.right != null
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    public void remove2(E e){
        root = remove2(root, e);
    }

    private Node remove2(Node node, E e){
        // 如果要删除的节点左右子树都存在，用前驱节点替换当前节点
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e) < 0){
            node.left = remove2(node.left, e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove2(node.right, e);
            return node;
        }
        else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // node.left != null, node.right != null
            Node predecessor = maximum(node.left);
            predecessor.left = removeMax(node.left);
            predecessor.right = node.right;
            node.left = node.right = null;
            return predecessor;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.e).append("\n");
        generateBSTString(node.left, depth+1, res);
        generateBSTString(node.right, depth+1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0; i< depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.preOrder();
//        Random rnd = new Random();
//
//        int n = 1000;
//        for(int i=0; i<n; i++)
//            bst.add(rnd.nextInt(10000));

//        ArrayList<Integer> nums = new ArrayList<>();
//        while(!bst.isEmpty())
//            nums.add(bst.removeMin());
//        System.out.println(nums);
//        for(int i=1; i<nums.size(); i++)
//            if(nums.get(i) < nums.get(i-1))
//                throw new IllegalArgumentException("Error");
//        System.out.println(bst.minimum());
//        System.out.println(bst.minimum2());
//        System.out.println(bst.maximum());
//        System.out.println(bst.maximum2());
    }

}
