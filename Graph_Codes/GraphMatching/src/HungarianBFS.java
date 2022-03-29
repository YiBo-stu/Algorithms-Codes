import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HungarianBFS {

    private Graph G;
    private int maxMatching = 0;
    // 存储顶点v匹配后，对应的匹配点是哪个
    private int[] matching;

    public HungarianBFS(Graph G){

        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartite())
            throw new IllegalArgumentException(
                    "HungarianBFS only works in bipartite graph.");
        this.G = G;
        int[] colors = bd.colors();
        matching = new int[G.V()];
        Arrays.fill(matching, -1);
        // Hungarian
        for(int v=0; v<G.V(); v++)
            // 从左侧的未匹配点开始寻找増广路径
            if(colors[v] == 0 && matching[v] == -1)
                if(bfs(v)) maxMatching ++;

    }

    private boolean bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        // 需要沿着増广路径修改信息，所以记录路径
        int[] pre = new int[G.V()];
        Arrays.fill(pre, -1);

        q.add(v);
        pre[v] = v;
        while(!q.isEmpty()){
            int cur = q.remove();
            // cur是左侧顶点，next是右侧顶点
            for(int next: G.adj(cur))
                if(pre[next] == -1){
                    if(matching[next] != -1){
                        pre[next] = cur;
                        pre[matching[next]] = next;
                        q.add(matching[next]);
                    }
                    else{
                        // 找到一条増广路径
                        pre[next] = cur;
                        ArrayList<Integer> augPath = getAugPath(pre, v, next);
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
        }
        return false;
    }

    private ArrayList<Integer> getAugPath(int[] pre, int start, int end){
        ArrayList<Integer> res = new ArrayList<>();
        int cur = end;
        while(cur != start){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(start);
        return res;
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        HungarianBFS hungarianBFS = new HungarianBFS(g);
        System.out.println(hungarianBFS.maxMatching());
        System.out.println(hungarianBFS.isPerfectMatching());

        Graph g2 = new Graph("g2.txt");
        HungarianBFS hungarianBFS2 = new HungarianBFS(g2);
        System.out.println(hungarianBFS2.maxMatching());
        System.out.println(hungarianBFS2.isPerfectMatching());
    }
}
