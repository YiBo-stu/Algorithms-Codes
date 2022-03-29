import java.util.TreeMap;

// HashTable的K不要求可比较，而具体实现用的是TreeMap又要求可比较，是个小bug
public class HashTable<K, V> {

    // 存放素数的数组，最后一个已经接近整型能存放的极限
    private final int[] capacity
    = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317,
        196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843,
        50331653, 100663319, 201326611, 402653189, 895306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;
    private TreeMap<K, V>[] hashtable;
    private int M;  // M就是哈希表的大小，是合适的素数
    private int size;

    public HashTable(){
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for(int i=0; i<M; i++)
            hashtable[i] = new TreeMap<>();
    }

    private int hash(K key){
        // 将key转换为hashTable数组里的索引
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key))
            // 如果已经存在，这里的逻辑是修改
            map.put(key, value);
        else{
            map.put(key, value);
            size ++;
            // 扩容操作
            if(size >= upperTol * M && capacityIndex + 1 < capacity.length){
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;
            // 缩容操作，这里要注意边界问题
            if(size < lowerTol * M && capacityIndex - 1 >= 0){
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist.");
        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM){
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for(int i=0; i<newM; i++)
            newHashTable[i] = new TreeMap<>();
        // 这里的逻辑要注意一下
        int oldM = M;
        this.M = newM;
        for(int i=0; i<oldM; i++){
            TreeMap<K, V> treeMap = hashtable[i];
            for(K key: treeMap.keySet()){
                newHashTable[hash(key)].put(key, treeMap.get(key));
            }
        }
        this.hashtable = newHashTable;
    }
}
