package Q1584;

import java.util.ArrayList;
import java.util.List;

// 别人的实现，提交时候就快很多
public class Solution3 {

    public int minCostConnectPoints(int[][] points) {
        //prim算法
        int N = points.length;
        int res = 0;
        int newPoints[][] = new int[N][N];
        //建立邻接矩阵
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                newPoints[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                newPoints[j][i] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }
        //lowcast存放与连接图中顶点的最小值，-1表示节点已经在mst中
        int lowcast[] = new int[N];
        List<Integer> count = new ArrayList<>();
        count.add(0);
        lowcast[0] = -1;
        for (int i = 1; i < N; i++) {
            lowcast[i] = newPoints[i][0];
        }
        while (count.size() < N) {
            //选取lowcast数组中与已在图中的顶点最小距离的点
            int minDistance = Integer.MAX_VALUE;
            int flag = 0;
            for (int i = 0; i < N; i++) {
                if (lowcast[i] > 0 && lowcast[i] < minDistance) {
                    flag = i;
                    minDistance = lowcast[i];
                }
            }
            //将选取的点加入图中
            count.add(flag);
            //计入最短距离
            res += minDistance;
            //更新lowcast
            for (int j = 0; j < N; j++) {
                if (j == flag) {
                    lowcast[j] = -1;
                } else {
                    if (lowcast[j] != -1)
                        lowcast[j] = Math.min(lowcast[j], newPoints[j][flag]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(new Solution().minCostConnectPoints(points));
    }
}
