import java.util.ArrayList;
import java.util.Stack;

public class EulerLoop {

    private Graph G;

    public EulerLoop(Graph G){
        this.G = G;
    }

    public boolean hasEulerLoop(){
        CC cc = new CC(G);
        if(cc.count() > 1) return false;
        for(int v=0; v<G.V(); v++)
            if(G.degree(v) % 2 == 1)
                return false;
        return true;
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()) return res;

        Graph g = (Graph) G.clone();
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        stack.push(cur);
        while(!stack.isEmpty()){
            if(g.degree(cur) != 0){
                stack.push(cur);
                int w = g.adj(cur).iterator().next();
                g.removeEdge(cur, w);
                cur = w;
            }
            else{
                res.add(cur);
                // 最后一次循环中stack.pop()的结果没有放入res，
                // 所以最开始进入队列的0不在结果中展示
                cur = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        EulerLoop el = new EulerLoop(g);
        System.out.println(el.result());
    }
}
