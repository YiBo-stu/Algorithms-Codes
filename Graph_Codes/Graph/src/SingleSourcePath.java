
import java.util.ArrayList;
import java.util.Collections;

public class SingleSourcePath {

    private Graph G;
    // 由于是单源路径，s代表源节点
    private int s;
    private boolean[] visited;
    // 存储每一个顶点之前的顶点是哪个
    private int[] pre;

    public SingleSourcePath(Graph G, int s){
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i=0; i<pre.length; i++)
            pre[i] = -1;
        dfs(s, s);
    }

    // parent是节点v的上一个节点
    private void dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w, v);
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t))
            // 这里是返回一个空列表，也可以用抛异常等方式
            return res;
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        // 将存储的元素颠倒顺序
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        SingleSourcePath ssPath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + ssPath.path(6));
        System.out.println("0 -> 5 : " + ssPath.path(5));
    }
}
