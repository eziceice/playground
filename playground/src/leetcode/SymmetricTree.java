package leetcode;

/**
 * Created by Ryan on 2017/5/15.
 */
public class SymmetricTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isMirror(root.left,root.right);
    }

    public boolean isMirror(TreeNode l, TreeNode r) {
        if (l == null && r == null)
            return true;
        if (l == null || r == null)
            return false;
        return (l.val == r.val) && isMirror(l.right, r.left) && isMirror(l.left,r.right);
    }
}
