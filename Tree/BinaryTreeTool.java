package zhang.algorithm.modelUtil.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/8
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 * <p>
 * 二叉树工具类
 */
public class BinaryTreeTool {

    public static boolean isBalanced(TreeNode root) {
        return treeHeight(root) < 0 ? false : true;
    }

    /**
     * 判断二叉树确实不能避免计算高度, 但我们可以在计算过程中进行一些优化
     *
     * @param root
     * @return
     */
    private static int treeHeight(TreeNode root) {
        if (root == null) return 0;
        int l = treeHeight(root.left);
        if (l < 0) return -1;
        int r = treeHeight(root.right);
        if (r < 0 || Math.abs(r - l) > 1) return -1;
        return Math.max(r, l) + 1;
    }

    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------






    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        BinaryTreeTool test = new BinaryTreeTool();
    }
}
