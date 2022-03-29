package Q207;

import java.util.TreeSet;

// 使用有向图的环检测算法
public class Solution {

    TreeSet<Integer>[] graph;
    private boolean[] visited;
    private boolean[] onPath;

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        graph = new TreeSet[numCourses];
        for(int i=0; i<numCourses; i++)
            graph[i] = new TreeSet<>();
        int E = prerequisites.length;
        for(int i=0; i<E; i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for(int v=0; v<numCourses; v++)
            if(!visited[v])
                if(dfs(v)){
                    return false;
                }
        return true;
    }

    private boolean dfs(int v){
        visited[v] = true;
        onPath[v] = true;
        for(int w: graph[v]){
            if(!visited[w]){
                if(dfs(w)) return true;
            }
            else if(onPath[w])
                return true;

        }
        onPath[v] = false;
        return false;
    }
}
