import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class EulerPath {

    private Graph G;
    private int start;

    public EulerPath(Graph G){
        this.G = G;
    }

    public boolean hasEulerPath(){
        CC cc = new CC(G);
        if(cc.count() > 1)
            return false;
        int oddEdge = 0;
        for(int v=0; v<G.V(); v++)
            if(G.degree(v) % 2 == 1){
                oddEdge ++;
                if(oddEdge == 1)
                    start = v;
            }
        if(oddEdge == 2)
            return true;
        return false;
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerPath())
            return res;
        Graph g = (Graph) G.clone();
        Stack<Integer> stack = new Stack<>();
        int cur = start;
        stack.push(start);
        while(!stack.isEmpty()){
            if(g.degree(cur) != 0){
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
        Graph g = new Graph("g2.txt");
        EulerPath eulerPath = new EulerPath(g);
        System.out.println(eulerPath.hasEulerPath());
        System.out.println(eulerPath.result());
    }
}
