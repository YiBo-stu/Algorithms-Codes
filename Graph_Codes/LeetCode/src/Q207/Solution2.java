package Q207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

// 使用拓扑排序检测环
public class Solution2 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        TreeSet<Integer>[] graph = new TreeSet[numCourses];
        for(int i=0; i<numCourses; i++)
            graph[i] = new TreeSet<>();
        int[] inDegrees = new int[numCourses];
        int E = prerequisites.length;
        for(int i=0; i<E; i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegrees[prerequisites[i][0]] ++;
        }
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<numCourses; i++)
            if(inDegrees[i] == 0)
                q.add(i);
        while(!q.isEmpty()){
            int cur = q.remove();
            res.add(cur);
            for(int next: graph[cur]){
                inDegrees[next] --;
                if(inDegrees[next] == 0)
                    q.add(next);
            }
        }
        if(res.size() != numCourses)
            return false;
        return true;
    }
}
