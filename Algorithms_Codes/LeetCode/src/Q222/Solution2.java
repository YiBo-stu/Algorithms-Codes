package Q222;

public class Solution2 {

    public int countNodes(TreeNode root) {

        if(root == null) return 0;
        int leftLevel = countLevel(root.left);
        int rightLevel = countLevel(root.right);
        if(leftLevel == rightLevel)
            return (1 << leftLevel) + countNodes(root.right);
        else
            return (1 << rightLevel) + countNodes(root.left);
    }

    private int countLevel(TreeNode node){
        int res = 0;
        while(node != null){
            res ++;
            node = node.left;
        }
        return res;
    }
}
