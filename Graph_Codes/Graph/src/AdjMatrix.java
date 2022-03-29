
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;
    // 一个工程相对路径的起始位置默认在工程文件夹(Graph)下

    public AdjMatrix(String filename){

        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0)
                throw new IllegalArgumentException("V must be non-negative.");
            adj = new int[V][V];
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
                if(adj[a][b] == 1)
                    throw new IllegalArgumentException("Parallel Edges are detected.");
                adj[a][b] = 1;
                adj[b][a] = 1;
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
        return adj[v][w] == 1;
    }

    // 返回和顶点v相邻的顶点
    public ArrayList<Integer> adj(int v){
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<V; i++)
            if(adj[v][i] == 1)
                res.add(i);
        return res;
    }

    // 计算节点的度
    public int degree(int v){
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("V = %d, E = %d\n", V, E));
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++)
                res.append(String.format("%d ", adj[i][j]));
            res.append('\n');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
