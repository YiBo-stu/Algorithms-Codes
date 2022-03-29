import java.util.*;

public class Path2 {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int s, t;

    public Path2(Graph G, int s, int t){
        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        bfs();
    }

    private void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        pre[s] = s;
        if(s == t) return;
        while(!q.isEmpty()){
            int cur = q.remove();
            for(int next: G.adj(cur))
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                    pre[next] = cur;
                    if(next == t)
                        return;
                }
        }
    }

    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnected()) return res;
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        Path2 path = new Path2(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());
        Path2 path1 = new Path2(g, 0, 1);
        System.out.println("0 -> 1 : " + path1.path());
        Path2 path2 = new Path2(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());
    }
}
