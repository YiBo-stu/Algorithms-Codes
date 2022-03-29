package Q257;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();
        if(root == null) return res;
        if(root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
            return res;
        }

        List<String> leftString = binaryTreePaths(root.left);
        List<String> rightString = binaryTreePaths(root.right);
        for(String s: leftString)
            res.add(root.val + "->" + s);
        for(String s: rightString)
            res.add(root.val + "->" + s);
        return res;
    }
}
