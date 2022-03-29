package Q733;

public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private int color;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor)
            return image;
        R = image.length;
        C = image[0].length;
        color = image[sr][sc];
        dfs(image, sr, sc, newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor){
        image[sr][sc] = newColor;
        for(int d=0; d<4; d++){
            int nextx = sr+dirs[d][0];
            int nexty = sc+dirs[d][1];
            if(inArea(nextx, nexty) && image[nextx][nexty]==color)
                dfs(image, nextx, nexty, newColor);
        }
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
