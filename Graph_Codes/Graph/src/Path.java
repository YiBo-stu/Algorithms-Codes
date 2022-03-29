
import java.util.ArrayList;
import java.util.Collections;

public class Path {

    private Graph G;
    // s代表源节点，t代表目标点
    private int s;
    private int t;
    private boolean[] visited;
    // 存储每一个顶点之前的顶点是哪个
    private int[] pre;

    public Path(Graph G, int s, int t){
        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i=0; i<pre.length; i++)
            pre[i] = -1;
        dfs(s, s);
        // 打印是否被访问过标记
        for(boolean e: visited)
            System.out.print(e + " ");
        System.out.println();
    }

    // 这里dfs有布尔型的返回值
    private boolean dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        if(v == t) return true;
        for(int w: G.adj(v))
            if(!visited[w])
                if(dfs(w, v)) return true;
        return false;
    }

    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnected())
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
        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());
        Path path1 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path1.path());
        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());
    }
}
