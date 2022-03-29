import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class AdjSet {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    // 一个工程相对路径的起始位置默认在工程文件夹(Graph)下

    public AdjSet(String filename){

        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0)
                throw new IllegalArgumentException("V must be non-negative.");
            // 数组中每个元素是对象，先申请整个数组的空间，之后再对每个元素分别申请空间
            adj = new TreeSet[V];
            for(int i=0; i<V; i++)
                adj[i] = new TreeSet<>();

            E = scanner.nextInt();
            if(E < 0)
                throw new IllegalArgumentException("E must be non-negative.");
            for(int i=0; i<E; i++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                if(a == b)
                    throw new IllegalArgumentException("Self loop is detected.");
                if(adj[a].contains(b))
                    throw new IllegalArgumentException("Parallel Edges are detected.");
                adj[a].add(b);
                adj[b].add(a);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // 经常要判断顶点对应的值是否合法，所以抽象为函数
    private void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid.");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    // 为了做到屏蔽实现细节，这里不返回TreeSet
    // 返回的是Iterable这个接口的对象
    // ArrayList, LinkedList, TreeSet, HashSet都实现了这个接口
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    // 计算节点的度
    public int degree(int v){
        validateVertex(v);
        // adj(v)返回的是Iterable的对象，没有规定有size()方法
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("V = %d, E = %d\n", V, E));
        for(int v=0; v<V; v++){
            res.append(String.format("%d : ", v));
            for(int w: adj[v])
                res.append(String.format("%d ", w));
            res.append('\n');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        AdjSet adjSet = new AdjSet("g.txt");
        System.out.println(adjSet);
    }
}
