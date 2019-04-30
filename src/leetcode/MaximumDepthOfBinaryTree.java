package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Ryan on 2017/4/30.
 */
public class MaximumDepthOfBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
    }

    /**
     * 非递归
     * @param root
     * @return
     */

    public int maxDepthNoRecrusive(TreeNode root) {
        if(root == null)
            return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            depth++;
            for(int i = 0;i < levelSize;i++){
                TreeNode top = queue.poll();
                if(top.left != null)
                    queue.offer(top.left);
                if(top.right != null)
                    queue.offer(top.right);
            }
        }
        return depth;
    }

}
