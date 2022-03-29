import java.util.Random;

public class SelectK {

    private int selectK(int[] arr, int l, int r, int k, Random rnd){

        int p = partition(arr, l, r, rnd);
        if(k == p)
            return arr[k];
        if(k < p)
            return selectK(arr, l, p-1, k, rnd);
        else
            return selectK(arr, p+1, r, k, rnd);
    }

    private int partition(int[] arr, int l, int r, Random rnd){

        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1, i-1] <= v, arr[j+1, r] >= v
        int i = l+1, j = r;
        while(true){
            while(i<=j && arr[i]<arr[l])
                i ++;
            while(j>=i && arr[j]>arr[l])
                j --;
            if(i >= j)
                break;
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
