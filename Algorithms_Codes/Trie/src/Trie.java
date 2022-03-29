import java.util.TreeMap;

// 不同于之前设计的数据结构，Trie并没有设计成范型类
// 相当于默认了使用Trie在每个节点中存储的字母都是Character类的对象
public class Trie {

    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    // Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个单词word，非递归实现
    public void add(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            size ++;
            cur.isWord = true;
        }
    }

    // 向Trie中添加一个单词word，递归实现
    public void addR(String word){
        addR(root, word, 0);
    }

    private void addR(Node node, String word, int index){
        if(index == word.length()){
            if(node.isWord) return;
            size ++;
            node.isWord = true;
            return;
        }
        char c = word.charAt(index);
        if(node.next.get(c) == null)
            node.next.put(c, new Node());
        node = node.next.get(c);
        addR(node, word, index + 1);
    }

    // 查询单词word是否在Trie中，非递归实现
    public boolean contains(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询单词word是否在Trie中，递归实现
    public boolean containsR(String word){
        return containsR(root, word, 0);
    }

    private boolean containsR(Node node, String word, int index){
        if(index == word.length())
            return node.isWord;
        char c = word.charAt(index);
        if(node.next.get(c) == null)
            return false;
        return containsR(node.next.get(c), word, index+1);
    }

    // 查询在Tire中是否有单词以prefix为前缀，单词本身也是自己的前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("apple");
        trie.add("apple");
        trie.addR("banana");
        trie.addR("banana");
        System.out.println(trie.getSize());
        System.out.println(trie.contains("apple"));
        System.out.println(trie.containsR("banana"));
        System.out.println(trie.containsR("banan"));
    }
}
