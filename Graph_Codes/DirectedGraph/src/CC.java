import java.util.ArrayList;

public class CC {
    // CC for Connected Component
    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public CC(Graph G){
        if(G.isDirected())
            throw new IllegalArgumentException("CC only works in undirected graph.");
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

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0, 5));
        ArrayList<Integer>[] comp = cc.components();
        for(int ccid = 0; ccid < comp.length; ccid++){
            System.out.print(ccid + " : ");
            for(int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
