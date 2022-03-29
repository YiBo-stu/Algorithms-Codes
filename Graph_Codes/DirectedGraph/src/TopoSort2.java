import java.util.ArrayList;
import java.util.Collections;

public class TopoSort2 {

    private Graph G;
    public ArrayList<Integer> res;
    public boolean hasCycle = false;

    public TopoSort2(Graph G){
        if(!G.isDirected())
            throw new IllegalArgumentException("TopoSort only works in directed graph.");
        this.G = G;
        res = new ArrayList<>();

        // 借助于有向图的环检测算法来检测是否有环
        hasCycle = new DirectedCycleDetection(G).hasCycle();
        if(hasCycle) return;
        GraphDFS dfs = new GraphDFS(G);
        for(int v: dfs.post())
            res.add(v);
        Collections.reverse(res);
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public Iterable<Integer> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        TopoSort2 topoSort2 = new TopoSort2(g);
        System.out.println(topoSort2.result());
    }
}
