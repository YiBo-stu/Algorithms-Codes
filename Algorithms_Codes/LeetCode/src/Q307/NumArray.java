package Q307;

public class NumArray {

    private interface Merger<E> {
        E merge(E a, E b);
    }

    public class SegmentTree<E> {

        private E[] data;
        private E[] tree;
        private Merger<E> merger;

        // Merger使得用户可以根据业务逻辑定义线段树的使用方式
        public SegmentTree(E[] arr, Merger<E> merger){
            this.merger = merger;
            data = (E[]) new Object[arr.length];
            for(int i=0; i<arr.length; i++)
                data[i] = arr[i];

            tree = (E[]) new Object[4 * arr.length];
            buildSegmentTree(0, 0, data.length - 1);
        }

        // 在treeIndex的位置创建表示区间[l ... r]的线段树
        // 需要注意treeIndex表示的是存放在tree中的位置，l和r则是data的区间端点
        private void buildSegmentTree(int treeIndex, int l, int r){
            if(l == r){
                tree[treeIndex] = data[l];
                return;
            }
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            int mid = l + (r - l) / 2;
            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid+1, r);
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        public E query(int queryL, int queryR){
            if(queryL < 0 || queryL >= data.length ||
                    queryR < 0 || queryR >= data.length || queryL > queryR){
                throw new IllegalArgumentException("Illegal query index.");
            }
            return query(0, 0, data.length-1, queryL, queryR);
        }

        // 这里的l和r对应tree[treeIndex]节点所包括的区间
        private E query(int treeIndex, int l, int r, int queryL, int queryR){
            if(queryL == l && queryR == r)
                return tree[treeIndex];

            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            // 如果当前节点表示的区间与查询区间不符则分三种情况考虑
            if(queryR <= mid)
                return query(leftTreeIndex, l, mid, queryL, queryR);

            else if(queryL >= mid + 1)
                return query(rightTreeIndex, mid+1, r, queryL, queryR);

            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid+1, r, mid+1, queryR);
            return merger.merge(leftResult, rightResult);
        }

        public void set(int index, E e){
            if(index < 0 || index >= data.length)
                throw new IllegalArgumentException("Illegal index.");
            data[index] = e;
            set(0, 0, data.length-1, index, e);
        }

        private void set(int treeIndex, int l, int r, int index, E e){
            if(l == r){
                tree[treeIndex] = e;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if(index <= mid)
                set(leftTreeIndex, l, mid, index, e);
            else if(index >= mid + 1)
                set(rightTreeIndex, mid+1, r, index, e);
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        public int getSize(){
            return data.length;
        }

        public E get(int index){
            if(index<0 || index>=data.length)
                throw new IllegalArgumentException("Illegal index.");
            return data[index];
        }

        private int leftChild(int index){
            return 2 * index + 1;
        }

        private int rightChild(int index){
            return 2 * index + 2;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("[");
            for(int i=0; i<tree.length; i++){
                if(tree[i] != null)
                    res.append(tree[i]);
                else
                    res.append("null");
                if(i != tree.length-1)
                    res.append(", ");
            }
            res.append("]");
            return res.toString();
        }
    }


    private Integer[] data;
    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        data = new Integer[nums.length];
        for(int i=0; i<nums.length; i++)
            data[i] = nums[i];
        segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
    }

    public void update(int index, int val) {
        segmentTree.set(index, val);
    }

    public int sumRange(int left, int right) {
        return segmentTree.query(left, right);
    }
}
