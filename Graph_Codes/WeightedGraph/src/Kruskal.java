import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    // mst for minimum spanning tree
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();

        // 计算联通分量的个数，联通分量大于1则不存在最小生成树
        CC cc = new CC(G);
        if(cc.count() > 1) return;

        // Kruskal
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for(int v=0; v<G.V(); v++)
            for(int w: G.adj(v))
                // 因为是无向边，图中又不存在自环边，所以只在v<w时才添加边
                if(v < w) edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
        Collections.sort(edges);

        // 每次取出一个边，判断与当前选择的边是否构成环，核心任务是判断是否有环
        UnionFind uf = new UnionFind(G.V());
        for(WeightedEdge edge: edges){
            int v = edge.getV();
            int w = edge.getW();
            if(!uf.isConnected(v, w)){
                mst.add(edge);
                uf.unionElements(v, w);
            }
        }
    }

    public Iterable<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
