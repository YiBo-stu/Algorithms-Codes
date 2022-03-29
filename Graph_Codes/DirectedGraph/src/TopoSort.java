import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSort {

    private Graph G;
    public ArrayList<Integer> res;
    public boolean hasCycle = false;

    public TopoSort(Graph G){
        if(!G.isDirected())
            throw new IllegalArgumentException("TopoSort only works in directed graph.");
        this.G = G;
        res = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();
        int[] indegrees = new int[G.V()];
        for(int v=0; v<G.V(); v++){
            indegrees[v] = G.indegree(v);
            if(indegrees[v] == 0) q.add(v);
        }
        while(!q.isEmpty()){
            int cur = q.remove();
            res.add(cur);
            for(int next: G.adj(cur)){
                indegrees[next] --;
                if(indegrees[next] == 0) q.add(next);
            }
        }
        if(res.size() != G.V()){
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public Iterable<Integer> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        TopoSort topoSort = new TopoSort(g);
        System.out.println(topoSort.result());
    }
}
