import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class DirectedEulerLoop {

    private Graph G;

    public DirectedEulerLoop(Graph G){
        if(!G.isDirected())
            throw new IllegalArgumentException(
                    "DirectedEulerLoop only works in directed graph.");
        this.G = G;
    }

    public boolean hasEulerLoop(){
        SCC scc = new SCC(G);
        if(scc.count() > 1) return false;
        for(int v=0; v<G.V(); v++)
            if(G.indegree(v) != G.outdegree(v))
                return false;
        return true;
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()) return res;

        // 求解欧拉回路过程中会删边，因此在原图的副本上进行操作
        Graph g = (Graph) G.clone();
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        stack.push(cur);
        while(!stack.isEmpty()){
            if(g.outdegree(cur) != 0){
                stack.push(cur);
                int w = g.adj(cur).iterator().next();
                g.removeEdge(cur, w);
                cur = w;
            }
            else{
                res.add(cur);
                cur = stack.pop();
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("ug.txt", true);
        DirectedEulerLoop el = new DirectedEulerLoop(g);
        System.out.println(el.result());

        Graph g2 = new Graph("ug2.txt", true);
        DirectedEulerLoop el2 = new DirectedEulerLoop(g2);
        System.out.println(el2.result());
    }
}

