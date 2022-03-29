package Q1584;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

// 超出时间限制
public class Solution {

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
        TreeMap<Integer, Integer>[] graph = new TreeMap[V];
        for(int i=0; i<V; i++)
            graph[i] = new TreeMap<>();
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(i < j){
                    int weight = Math.abs(points[i][0] - points[j][0]) +
                            Math.abs(points[i][1] - points[j][1]);
                    graph[i].put(j, weight);
                    graph[j].put(i, weight);
                }
            }
        }

        // Prim
        int minCost = 0;
        boolean[] visited = new boolean[V];
        visited[0] = true;
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        for(int w: graph[0].keySet()){
            pq.add(new WeightedEdge(0, w, graph[0].get(w)));
        }
        while(!pq.isEmpty()){
            WeightedEdge minEdge = pq.remove();
            if(visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;
            minCost += minEdge.getWeight();
            int newV = visited[minEdge.getV()]? minEdge.getW(): minEdge.getV();
            visited[newV] = true;
            for(int w: graph[newV].keySet()){
                pq.add(new WeightedEdge(newV, w, graph[newV].get(w)));
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(new Solution().minCostConnectPoints(points));
    }
}
