package Q1584;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution2 {

    private class WeightedEdge implements Comparable<WeightedEdge>{
        public int v, w, weight;
        public WeightedEdge(int v, int w, int weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        public int getV() {
            return v;
        }
        public int getW(){
            return w;
        }
        public int getWeight(){
            return weight;
        }
        @Override
        public int compareTo(WeightedEdge o) {
            return weight - o.weight;
        }
    }

    public int minCostConnectPoints(int[][] points) {

        int V = points.length;
        // Prim
        int minCost = 0;
        boolean[] visited = new boolean[V];
        visited[0] = true;
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        int weight;
        for(int i=1; i<V; i++){
            weight = Math.abs(points[i][0] - points[0][0]) + Math.abs(points[i][1] - points[0][1]);
            pq.add(new WeightedEdge(0, i, weight));
        }
        while(!pq.isEmpty()){
            WeightedEdge minEdge = pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;
            minCost += minEdge.getWeight();
            int newV = visited[minEdge.getV()]? minEdge.getW(): minEdge.getV();
            visited[newV] = true;
            for(int i=0; i<V; i++){
                if(newV != i){
                    weight = Math.abs(points[i][0] - points[newV][0]) + Math.abs(points[i][1] - points[newV][1]);
                    pq.add(new WeightedEdge(newV, i, weight));
                }
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(new Solution().minCostConnectPoints(points));
    }
}
