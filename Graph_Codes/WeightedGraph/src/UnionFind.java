
public class UnionFind{

    private int[] parent;
    private int[] rank;

    public UnionFind(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i=0; i<size; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int getSize() {
        return parent.length;
    }

    // 查找p所对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p){
        if(p<0 || p>=parent.length)
            throw new IllegalArgumentException("index out of bound");
        while(p != parent[p]){
            // 路径压缩的过程
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // O(h)复杂度，h为树的高度
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(h)复杂度，h为树的高度
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;
        // 在路径压缩过程中节点层数发生了改变，但是并没有维护rank数组
        // rank已经不再表示以当前节点为根节点的树的深度，这也是叫rank而不是depth的原因
        // rank只是作为合并时的一个参考，依然可以胜任参考的工作，但并不实际反应节点的高度值
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}

