package Q378;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int l = matrix[0][0], r = matrix[row-1][col-1];
        int mid, count;
        while(l < r){
            mid = l + (r - l) / 2;
            count = countSE(matrix, mid, row, col);
            if(count >= k){
                r = mid;
            }
            else
                l = mid + 1;
        }
        return l;
    }

    private int countSE(int[][] matrix, int mid, int row, int col){
        int i = row - 1, j = 0;
        int count = 0;
        while(i >= 0 && j < col){
            if(matrix[i][j] <= mid){
                count += i + 1;
                j ++;
            }
            else{
                i --;
            }
        }
        return count;
    }
}
