import java.util.*;

public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;

    private class Node implements Comparable<Node> {

        public int v, dis;

        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }

    public Dijkstra(WeightedGraph G, int s){
        this.G = G;
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        dis[s] = 0;
        pre[s] = s;
        visited = new boolean[G.V()];

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while(!pq.isEmpty()){
            int cur = pq.remove().v;
            // 优先队列中一个顶点对应的Node可能不止一个，如果已经被处理过则跳过
            if(visited[cur]) continue;
            visited[cur] = true;
            for(int w: G.adj(cur)){
                if(!visited[w] && dis[cur] + G.getWeight(cur, w) < dis[w]){
                    dis[w] = dis[cur] + G.getWeight(cur, w);
                    pre[w] = cur;
                    // 更新dis[w]后优先队列中的值并没有改变，因此优先队列中要加入一个Node
                    pq.add(new Node(w, dis[w]));
                }
            }
        }
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v){
        G.validateVertex(v);
        return dis[v];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) return res;
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
        WeightedGraph g = new WeightedGraph("g2.txt");
        Dijkstra dijkstra = new Dijkstra(g, 0);
        for(int v=0; v<g.V(); v++)
            System.out.print(dijkstra.distTo(v) + " ");
        System.out.println();

        System.out.println(dijkstra.path(3));
    }
}
