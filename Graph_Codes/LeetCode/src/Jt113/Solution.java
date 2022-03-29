package Jt113;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        TreeSet<Integer>[] graph = new TreeSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new TreeSet<>();
        }
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegrees[prerequisites[i][0]] ++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(inDegrees[i] == 0)
                q.add(i);
        }
        int[] res = new int[numCourses];
        int index = 0;
        while(!q.isEmpty()){
            int cur = q.remove();
            res[index ++] = cur;
            for(int w: graph[cur]){
                if(-- inDegrees[w] == 0)
                    q.add(w);
            }
        }
        if(index < numCourses)
            return new int[0];
        return res;
    }
}
