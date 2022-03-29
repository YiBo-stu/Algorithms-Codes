
public class UnionFind1 implements UF{
    // QuickFind
    private int[] id;
    public UnionFind1(int size){
        id = new int[size];
        // 初始时每个元素都属于不同的集合
        for(int i=0; i<size; i++)
            id[i] = i;
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // 查找元素对应的集合编号，O(1)复杂度
    private int find(int index){
        if(index<0 || index>=id.length){
            throw new IllegalArgumentException("index out of bound.");
        }
        return id[index];
    }

    // 查看元素p和元素q是否属于同一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if(pID == qID)
            return;
        for(int i=0; i<getSize(); i++){
            if(id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
