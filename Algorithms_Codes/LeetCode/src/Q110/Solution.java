package Q110;

// 自顶向下的递归
public class Solution {

    public boolean isBalanced(TreeNode root) {

        if(root == null) return true;
        if(isBalanced(root.left) && isBalanced(root.right))
            return Math.abs(depth(root.left) - depth(root.right)) <= 1;
        else
            return false;
    }

    private int depth(TreeNode root){

        if(root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
