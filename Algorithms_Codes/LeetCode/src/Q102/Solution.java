package Q102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, res);
        return res;
    }

    private void levelOrder(TreeNode node, List<List<Integer>> res){

        if(node == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            int n = queue.size();
            List<Integer> layer = new ArrayList<>();
            for(int i=0; i<n; i++){
                TreeNode cur = queue.remove();
                layer.add(cur.val);
                if(cur.left != null)
                    queue.add(cur.left);
                if(cur.right != null)
                    queue.add(cur.right);
            }
            res.add(layer);
        }
    }
}
