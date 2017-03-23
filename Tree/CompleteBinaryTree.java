package zhang.algorithm.modelUtil.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/15
 * Time: 上午11:45
 * To change this template use File | Settings | File Templates.
 */
public class CompleteBinaryTree {
    /**
     * 方法一: 通过层次遍历, O(N)
     * 方法二: 通过判断[左子树的最右]和[右子树的最右]的高度来判断, O(lgN)
     *
     * @param root
     * @return
     */
    public TreeNode findInsertNode(TreeNode root) {
        if (root == null) return null;
        if (root.left == null || root.right == null) return root;

        int lh = 0, rh = 0;
        TreeNode tmp = root.left;
        while (tmp != null) {
            tmp = tmp.right;
            lh++;
        }
        tmp = root.right;
        while (tmp != null) {
            tmp = tmp.right;
            rh++;
        }

        if (lh == rh) return findInsertNode(root.left);
        else return findInsertNode(root.right);
    }


    public static void main(String[] args) {
        CompleteBinaryTree test = new CompleteBinaryTree();
        int[] tree = {1, 2, 3, 4, 5, 6};
        System.out.println(test.findInsertNode(BinaryTree.instance(tree).getRoot()));
    }
}
