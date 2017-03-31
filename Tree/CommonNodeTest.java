package zhang.algorithm.modelUtil.Tree;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/3/28
 * Time: 下午8:03
 * To change this template use File | Settings | File Templates.
 * [百度实习面试题]
 * 给一个左孩子右兄弟二叉树和两个结点,找到其父节点
 */
public class CommonNodeTest {
    //--------------------------------------------------------------------------------------------------------
    //方法一:遍历两遍, 时间复杂度为 O(N) + O(N * logN)
    //--------------------------------------------------------------------------------------------------------

    /**
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode fun1(TreeNode root, TreeNode p, TreeNode q) {
        fun2(root, p, q);
        if (common != null) return common;
        else {
            if (mark == null) return null;
            else return fun3(root, mark);
        }
    }

    TreeNode common = null; //公共父节点
    TreeNode mark = null;

    /**
     * 遍历一遍左孩子, 右兄弟二叉树, 找到commonRoot or mark
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode fun2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;

        TreeNode tmp = null;
        if (root.val == p.val || root.val == q.val) tmp = root;  //比较值

        TreeNode left = fun2(root.left, p, q);
        TreeNode right = fun2(root.right, p, q);
        if (tmp != null && left != null) common = root;
        else if ((tmp != null && right != null) || (left != null && right != null)) mark = root;

        return tmp != null ? tmp : (left != null ? left : right);
    }

    /**
     * 根据找到的mark寻找其父节点
     *
     * @param root
     * @param mark
     * @return
     */
    public TreeNode fun3(TreeNode root, TreeNode mark) {
        if (root == null) return null;

        TreeNode cur = root.left;
        while (cur != null && cur.val != mark.val)  //比较值
            cur = cur.right;
        if (cur != null) return root;

        TreeNode left = fun3(root.left, mark);
        if (left != null) return left;
        else return fun3(root.right, mark);
    }

    //--------------------------------------------------------------------------------------------------------
    //方法二: 每次使用int值返回后面遍历的状态, 只遍历一次, 时间复杂度为 O(N)
    //--------------------------------------------------------------------------------------------------------

    TreeNode node = null;

    public TreeNode gun1(TreeNode root, TreeNode p, TreeNode q) {
        gun2(root, p, q);
        return node;
    }

    /**
     * 用cnt(0, 1, -2, 2)表示找到多少个结点
     * 分别表示:
     * 0  -->  找到0个结点
     * 1  -->  找到1个结点
     * 2  -->  找到2个结点, 并且找到根结点
     * -2 -->  找到2个结点, 没有找到根结点, 需要往上回溯继续找跟结点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public int gun2(TreeNode root, TreeNode p, TreeNode q) {
        int cnt = 0;
        if (root == null) return cnt;

        //left
        int left = gun2(root.left, p, q);
        if (Math.abs(left) == 2) {
            if (left == -2) node = root;
            return 2;
        }

        //left、root
        if (root.val == p.val || root.val == q.val) cnt++;
        if (left + cnt == 2) {
            node = root;
            return 2;
        }

        //right
        int right = gun2(root.right, p, q);
        if (Math.abs(right) == 2) return right;

        //left、right 和 root、right
        if (cnt + left + right == 2) return -2;
        else return cnt + left + right;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        CommonNodeTest test = new CommonNodeTest();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        BinaryTree tree = BinaryTree.instance(nums);
        System.out.println(tree);
        TreeNode root = tree.getRoot();
        TreeNode p = new TreeNode(6);
        TreeNode q = new TreeNode(12);
        System.out.println(test.fun1(root, p, q));
        System.out.println(test.gun1(root, p, q));
    }
}
