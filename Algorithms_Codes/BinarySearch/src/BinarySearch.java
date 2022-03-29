import javax.swing.*;

public class BinarySearch {

    private BinarySearch(){}

    // 二分查找的非递归实现
    public static <E extends Comparable<E>> int search(E[] data, E target){
        int l = 0, r = data.length-1;
        while(l <= r){
            // l == r 时 mid = l
            int mid = l + (r - l) / 2;
            if(data[mid].compareTo(target) == 0)
                return mid;
            if(data[mid].compareTo(target) < 0)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    // 二分查找的递归实现
    public static <E extends Comparable<E>> int searchR(E[] data, E target){
        return searchR(data, 0, data.length-1, target);
    }

    private static <E extends Comparable<E>> int searchR(E[]data, int l, int r, E target){
        if(l > r) return -1;
        int mid = l + (r - l) / 2;
        if(data[mid].compareTo(target) == 0)
            return mid;
        else if(data[mid].compareTo(target) > 0)
            return searchR(data, l, mid-1, target);
        else
            return searchR(data, mid+1, r, target);
    }

    // 大于target的最小值的索引
    public static <E extends Comparable<E>> int upper(E[] data, E target){

        int l = 0, r = data.length;
        // 在data[l, r]中寻找解，因为解一定存在于搜索范围，当l==r时就是解
        while(l < r){
            int mid = l + (r - l) / 2;
            if(target.compareTo(data[mid]) < 0){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return r;
    }

    // 如果存在与target值相等的元素，则返回最大的索引，否则返回upper
    public static <E extends Comparable<E>> int upperCeil(E[] data, E target){
        int u = upper(data, target);
        if(u-1>= 0 && data[u-1].compareTo(target) == 0)
            return u - 1;
        return u;
    }

    // 如果存在与target值相等的元素，则返回最小的索引，否则返回upper
    public static <E extends Comparable<E>> int lowerCeil(E[] data, E target){
        int u = upper(data, target);
        if(u-1>=0 && data[u-1].compareTo(target)==0){
            while(u-1>=0 && data[u-1].compareTo(target)==0){
                u --;
            }
        }
        return u;
    }

    // 小于target的最大值的索引
    public static <E extends Comparable<E>> int lower(E[] data, E target){
        int l = -1, r = data.length-1;
        while(l < r){
            //
            int mid = l + (r - l + 1) / 2;
            if(data[mid].compareTo(target) < 0)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    // 如果存在与target值相等的元素，则返回最小的索引，否则返回lower
    public static <E extends Comparable<E>> int lowerFloor(E[] data, E target){
        int l = lower(data, target);
        if(l+1<=data.length-1 && data[l+1].compareTo(target)==0){
            return l + 1;
        }
        return l;
    }

    // 如果存在与target值相等的元素，则返回最大的索引，否则返回lower
    public static <E extends Comparable<E>> int upperFloor(E[] data, E target){
        int l = lower(data, target);
        if(l+1<=data.length-1 && data[l+1].compareTo(target)==0){
            while(l+1<=data.length-1 && data[l+1].compareTo(target)==0)
                l ++;
        }
        return l;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 1, 3, 3, 5, 5};
        for(int i=0; i<=6; i++)
            System.out.println(BinarySearch.upperFloor(arr, i));
    }
}
