import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {

    private Graph G;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G){
        this.G = G;
        pre = new int[G.V()];
        end = -1;

        int visited = 0;
        dfs(visited, 0, 0, G.V());
    }

    // left表示还剩下多少节点没有遍历到，这样判断是否所有节点都已经被遍历比较省时
    private boolean dfs(int visited, int v, int parent, int left){
        visited += (1 << v);
        pre[v] = parent;
        left --;
        /*
        把递归终止条件写在这里也是可以的
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }
        */
        for(int w: G.adj(v))
            if((visited & (1 << w)) == 0){
                // 注意这个大括号不能省略，否则后面的else if与括号里面的if配对
                if(dfs(visited, w, v, left)) return true;
            }
            else if(w == 0 && left == 0){
                end = v;
                return true;
            }
        visited -= (1 << v);
        // 此处left ++没有意义，因为是值传递
        return false;
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;
        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());

        // g2.txt保存的是哈密尔顿原始问题的图
        Graph g2 = new Graph("g2.txt");
        HamiltonLoop hl2 = new HamiltonLoop(g2);
        System.out.println(hl2.result());
    }
}
