package Q11;

public class Solution {

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int maxArea = Math.min(height[i], height[j]) * (j - i);
        int area;
        while(i != j){
            if(height[i] < height[j]){
                area = Math.min(height[++ i], height[j]) * (j - i);
            }
            else{
                area = Math.min(height[i], height[-- j]) * (j - i);
            }
            if(area > maxArea)
                maxArea = area;
        }
        return maxArea;
    }
}
