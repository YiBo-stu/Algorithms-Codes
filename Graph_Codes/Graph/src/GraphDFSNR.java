import java.util.ArrayList;
import java.util.Stack;

public class GraphDFSNR {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();

    public GraphDFSNR(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for(int v=0; v< G.V(); v++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        while(!stack.isEmpty()){
            int cur = stack.pop();
            pre.add(cur);
            for(int next: G.adj(cur))
                if(!visited[next]){
                    stack.push(next);
                    visited[next] = true;
                }
        }
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        GraphDFSNR dfsnr = new GraphDFSNR(g);
        System.out.println(dfsnr.pre());
    }
}
