package Q208;

import java.util.TreeMap;

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

    public Trie(){
        root = new Node();
    }

    // 向Trie中添加一个单词word
    public void insert(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
        }
    }

    // 查询单词word是否在Trie中
    public boolean search(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    // 查询在Tire中是否有单词以prefix为前缀，单词本身也是自己的前缀
    public boolean startsWith(String prefix){
        Node cur = root;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }
}
