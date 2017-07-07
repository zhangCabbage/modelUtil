package zhang.algorithm.modelUtil.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/7
 * Time: 下午12:04
 * To change this template use File | Settings | File Templates.
 */
public class BST2LinkedList {

    private TreeNode pre = null;

    private TreeNode head = null;

    /**
     * 原版,代码
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        Convert1(pRootOfTree.left);
        if (pre == null) {
            pre = pRootOfTree;
            head = pRootOfTree;
        } else {
            pre.right = pRootOfTree;
            pRootOfTree.left = pre;
            pre = pRootOfTree;
        }
        Convert1(pRootOfTree.right);
        return head;
    }

    /**
     * 改进版本
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        TreeNode left = Convert2(pRootOfTree.left);
        if (pre == null) {
            pre = pRootOfTree;
        } else {
            pre.right = pRootOfTree;
            pRootOfTree.left = pre;
            pre = pRootOfTree;
        }
        Convert2(pRootOfTree.right);
        return left == null ? pRootOfTree : left;
    }

    public static void main(String[] args) {
        BST2LinkedList test = new BST2LinkedList();
    }
}
