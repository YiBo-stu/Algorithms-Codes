import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// 暂时只支持无向带权图
public class WeightedGraph implements Cloneable{

    private int V;
    private int E;
    // 第一个Integer指的是与v相邻的顶点w，第二个Integer指的是v与w之间的权重
    private TreeMap<Integer, Integer>[] adj;

    public WeightedGraph(String filename){
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative.");
            // 数组中每个元素是TreeMap对象，先申请整个数组的空间，之后再对每个元素分别申请空间
            adj = new TreeMap[V];
            for(int i=0; i<V; i++)
                adj[i] = new TreeMap<>();
            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative.");
            for(int i=0; i<E; i++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                int weight = scanner.nextInt();
                if(a == b) throw new IllegalArgumentException("Self loop is detected.");
                if(adj[a].containsKey(b))
                    // 带权图中可以有平行边，但通常只用权值小的那条，这里不考虑平行边
                    throw new IllegalArgumentException("Parallel Edges are detected.");
                adj[a].put(b, weight);
                adj[b].put(a, weight);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // 经常要判断顶点对应的值是否合法，所以抽象为函数
    public void validateVertex(int v){
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
        return adj[v].containsKey(w);
    }

    // 这里只返回与顶点v相邻的顶点，而不返回它们的权值
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v].keySet();
    }

    public int getWeight(int v, int w){
        if(hasEdge(v, w))
            return adj[v].get(w);
        throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
    }

    public int degree(int v){
        validateVertex(v);
        // adj(v)返回的是Iterable的对象，没有规定有size()方法
        return adj[v].size();
    }

    // 这是寻找欧拉回路时用到的删边操作，这里其实不需要
    public void removeEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Object clone(){
        // 默认的clone是浅拷贝，clone方法实现类的深拷贝
        try{
            WeightedGraph cloned = (WeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for(int v=0; v<V; v++){
                cloned.adj[v] = new TreeMap<>();
                // TreeMap、HashMap都可以使用这种方式进行遍历，这种方式比较推荐
                for(Map.Entry<Integer, Integer> entry: adj[v].entrySet())
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
            }
            return cloned;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("V = %d, E = %d\n", V, E));
        for(int v=0; v<V; v++){
            res.append(String.format("%d : ", v));
            // 这里依然是使用Map.Entry的方法取出键值数据对
            for(Map.Entry<Integer, Integer> entry: adj[v].entrySet())
                res.append(String.format("(%d: %d)", entry.getKey(), entry.getValue()));
            res.append('\n');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("g.txt");
        System.out.println(graph);
    }
}
