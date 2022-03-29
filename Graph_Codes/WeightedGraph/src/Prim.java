import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Prim(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return;

        //Prim
        // visited数组用于记录对于图的切分
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        for(int w: G.adj(0))
            pq.add(new WeightedEdge(0, w, G.getWeight(0, w)));
        while(!pq.isEmpty()){
            WeightedEdge minEdge = pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;
            mst.add(minEdge);
            int newv = visited[minEdge.getV()] ? minEdge.getW(): minEdge.getV();
            visited[newv] = true;
            for(int w: G.adj(newv))
                if(!visited[w])
                    pq.add(new WeightedEdge(newv, w, G.getWeight(newv, w)));
        }
    }

    public Iterable<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g.txt");
        Prim prim = new Prim(g);
        System.out.println(prim.result());
    }
}
