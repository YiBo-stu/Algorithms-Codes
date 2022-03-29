import java.util.ArrayList;

public class CC {
    // CC for Connected Component
    private WeightedGraph G;
    private int[] visited;
    private int cccount = 0;

    public CC(WeightedGraph G){
        this.G = G;
        // visited用于记录是否被遍历过以及所属的联通分量
        visited = new int[G.V()];
        for(int i=0; i<visited.length; i++)
            visited[i] = -1;
        for(int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int v, int ccid){
        visited[v] = ccid;
        for(int w: G.adj(v))
            if(visited[w] == -1)
                dfs(w, ccid);
    }

    public int count(){
        return cccount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for(int i=0; i<cccount; i++)
            res[i] = new ArrayList<>();
        for(int v=0; v<G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }
}
