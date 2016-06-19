package zhang.algorithm.modelUtil.Tree;


/**
 *
 * Created by zhang_zack on 16/6/5.
 * 针对leetcode练习专用建立的二叉树类
 *
 */
public class TreeNode {
    /**
     * 数节点值
     */
    public int val;
    /**
     * 左子树
     */
    public TreeNode left;
    /**
     * 右子树
     */
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "the value of this node is => "+val;
    }
}
