package Q110;

// 自底向上的递归
public class Solution2 {

    public boolean isBalanced(TreeNode root) {

        return depth(root) != -1;
    }

    private int depth(TreeNode root){

        if(root == null) return 0;
        int leftDepth, rightDepth;
        if((leftDepth = depth(root.left)) == -1 || (rightDepth = depth(root.right)) == -1 ||
                Math.abs(leftDepth - rightDepth) > 1)
            return -1;
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
