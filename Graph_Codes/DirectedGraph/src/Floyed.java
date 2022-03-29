import java.util.Arrays;

public class Floyed {

    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegativeCycle = false;

    public Floyed(WeightedGraph G){
        this.G = G;
        dis = new int[G.V()][G.V()];
        for(int i=0; i<G.V(); i++)
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        for(int v=0; v<G.V(); v++){
            dis[v][v] = 0;
            for(int w: G.adj(v))
                dis[v][w] = G.getWeight(v, w);
        }

        for(int t=0; t<G.V(); t++)
            for(int v=0; v< G.V(); v++)
                for(int w=0; w< G.V(); w++)
                    // 如果dis[v][t]和dis[t][w]中任意一个是无穷大，那么它们的和不会小于dis[v][w]
                    if(dis[v][t] != Integer.MAX_VALUE && dis[t][w] != Integer.MAX_VALUE &&
                            dis[v][t] + dis[t][w] < dis[v][w])
                        dis[v][w] = dis[v][t] + dis[t][w];

        for(int v=0; v<G.V(); v++)
            if(dis[v][v] < 0){
                hasNegativeCycle = true;
                break;
            }

    }

    public boolean hasNegCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnectedTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int distTo(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return dis[v][w];
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("wg2.txt", true);
        Floyed floyed = new Floyed(g);
        if(!floyed.hasNegCycle()){
            for(int v=0; v<g.V(); v++){
                for(int w=0; w<g.V(); w++)
                    System.out.print(floyed.distTo(v, w) + " ");
                System.out.println();
            }
        }
        else{
            System.out.println("exist negative cycle");
        }
    }

}
