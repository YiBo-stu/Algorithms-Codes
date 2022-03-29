import java.util.ArrayList;

public class GraphDFS {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        // 如果只是调用dfs(0)，对于有多个联通分量的图，不能完成所有顶点的遍历
        for(int v = 0; v < G.V(); v++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){
        visited[v] = true;
        pre.add(v);
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }
}
