package zhang.algorithm.modelUtil.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/5
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public class HasSubTree {
    /**
     * 判断root2是不是root1的子树, 如果root2为null, 不是任意树的子树
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return isSame(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private static boolean isSame(TreeNode root1, TreeNode root2) {
        // 注意:
        // 子树root2如果为null, 那么root2为root1的子树, 可以理解成ok
        // 但是如果root2不为null, root1为null, 那就不是了
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val == root2.val)
            return isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        return false;
    }

    public static void main(String[] args) {
        TreeNode root1 = BinaryTree.instance(new int[]{8, 0, 8, 0, 9, 0, 2, 0, 5}).getRoot();
        TreeNode root2 = BinaryTree.instance(new int[]{8, 0, 9, 3, 2}).getRoot();
        System.out.println(HasSubtree(root1, root2));
    }
}
