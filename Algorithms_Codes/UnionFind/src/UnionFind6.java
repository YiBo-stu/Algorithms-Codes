public class UnionFind6 implements UF{
    // QuickUnion
    private int[] parent;
    private int[] rank;

    public UnionFind6(int size){
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
        // 现在的find函数式递归函数
        if(p<0 || p>=parent.length)
            throw new IllegalArgumentException("index out of bound");
        if(p != parent[p])
            // 与第5版的区别在于这里使用递归的方式实现路径压缩，使每个节点直接指向其根节点
            parent[p] = find(parent[p]);
        return parent[p];
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
        // rank已经不再表示以当前节点为根节点的树的深度
        // 基于现在的rank执行下面的逻辑是没有问题的
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

