
public class UnionFind4 implements UF{
    // rank based
    private int[] parent;
    private int[] rank;

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i=0; i<size; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找p所对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p){
        if(p<0 || p>=parent.length)
            throw new IllegalArgumentException("index out of bound");
        while(p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    // O(h)复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(h)复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else{ // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}

