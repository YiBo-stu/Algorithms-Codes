
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// Unweighted single source shortest path
public class USSSPath {

    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;
    private int[] dis;

    public USSSPath(Graph G, int s){
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        for(int i=0; i<G.V(); i++){
            pre[i] = -1;
            dis[i] = -1;
        }
        bfs(s);
        // 打印每个点与起始点的距离
        for(int i=0; i<G.V(); i++)
            System.out.print(dis[i] + " ");
        System.out.println();
    }

    private void bfs(int s){
        // 借用队列来实现图的广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t))
            return res;
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);
        Collections.reverse(res);
        return res;
    }

    public int dis(int t){
        G.validateVertex(t);
        return dis[t];
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        USSSPath usssPath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + usssPath.path(6));
        System.out.println(usssPath.dis(6));
    }
}
