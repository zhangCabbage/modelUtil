package zhang.algorithm.modelUtil.Tree;

import zhang.algorithm.modelUtil.String.StringTool;

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

    /**
     * 对一个二叉树进行序列化
     *
     * @param tree
     * @return
     */
    public static String Serialize(BinaryTree tree) {
        return Serialize(tree.getRoot());
    }

    private static StringBuilder sb;

    public static String Serialize(TreeNode root) {
        sb = new StringBuilder();
        serializePreOrder(root);
        return sb.toString();
    }

    private static void serializePreOrder(TreeNode root) {
        if (root == null) {
            sb.append("$,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        serializePreOrder(root.left);
        serializePreOrder(root.right);
    }

    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------

    private static int index = 0;

    /**
     * 反序列化二叉树
     * 把下标放在外面, 省去了左右子树迭代的下标判断!
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static BinaryTree Deserialize(String str) throws Exception {
        index = 0;
        BinaryTree tree = new BinaryTree();
        tree.setRoot(deserializeHelp(str));
        return tree;
    }

    private static TreeNode deserializeHelp(String str) throws Exception {
        if (str == null || str.length() < 1) return null;

        int start = index;
        for (; index < str.length(); index++) {
            char c = str.charAt(index);
            if (c == ',') {
                String tmp = str.substring(start, index++);
                if ("$".equals(tmp)) {
                    return null;
                }
                if (!StringTool.isNumeric(tmp)) throw new Exception("不是一个正规的序列化字符串");
                TreeNode root = new TreeNode(Integer.valueOf(tmp));
                root.left = deserializeHelp(str);
                root.right = deserializeHelp(str);
                return root;
            }
        }

        throw new Exception("不是一个正规的序列化字符串");
    }

    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        System.out.println(Deserialize("1,$,2,$,$,"));
    }
}
