package zhang.algorithm.modelUtil.AlgorithmDesign.Graph;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 16/9/25
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 * <p>
 * [线段树] 的实现, 用满二叉树存储线段树
 * <p>
 * 注意: 理解线段树的区间更新中的延迟标记
 * [一步一步理解线段树]
 * (http://www.cnblogs.com/TenosDoIt/p/3453089.html)
 * <p>
 * 这里最大的理解障碍就是其中mark的使用!!
 */
public class SegmentTree {
    public SegNode[] segNodes;

    /**
     * 新建线段树时, 确保知道需要存储的所有节点的数目, 初始化每个节点。
     *
     * @param left
     * @param right
     */
    public SegmentTree(int left, int right) {
        //满二叉树所需要的节点数
        int total = (int) Math.pow(2, Math.ceil(Math.log(right - left + 1) / Math.log(2)) + 1);
        this.segNodes = new SegNode[total];
        for (int i = 0; i < this.segNodes.length; i++) {
            this.segNodes[i] = new SegNode();
        }
        build(0, left, right);
    }

    /**
     * @param root
     * @param l
     * @param r
     */
    public void build(int root, int l, int r) {
        segNodes[root].l = l;
        segNodes[root].r = r;

        if (l + 1 != r) {
            int mid = l + (r - l) / 2;
            build(root * 2 + 1, l, mid);
            build(root * 2 + 2, mid, r);
        }
    }

    /**
     * 当mark为负, 删除
     * 当mark为正, 插入
     *
     * @param root
     * @param l
     * @param r
     * @param mark
     */
    public void insert(int root, int l, int r, int mark) {
        SegNode segNode = segNodes[root];
        if (segNode.l == l && segNode.r == r) {
            segNode.mark += mark;
        } else {
            int mid = segNode.l + (segNode.r - segNode.l) / 2;
            if (r <= mid) {
                insert(root * 2 + 1, l, r, mark);
            } else if (l >= mid) {
                insert(root * 2 + 2, l, r, mark);
            } else {
                insert(root * 2 + 1, l, mid, mark);
                insert(root * 2 + 2, mid, r, mark);
            }
        }
        //插入完成之后再更新,len、lines、lbd、rbd等字段
        //!!!
        update(root);
    }

    /**
     * 更新线段树中的线段后, 再更新线段中的各个字段
     *
     * @param root
     */
    public void update(int root) {
        SegNode segNode = segNodes[root];
        if (segNode.mark > 0) {
            segNode.lbd = segNode.rbd = segNode.lines = 1;
            segNode.len = segNode.r - segNode.l;
        } else if (segNode.l + 1 == segNode.r) {
            //当mark == 0 或 -1 时, 此线段需被删除, 线段重置
            //叶子节点的操作
            segNode.clear();
        } else {
            //非叶子节点并且mark值不为1的节点的操作
            SegNode lNode = segNodes[root * 2 + 1];
            SegNode rNode = segNodes[root * 2 + 2];
            segNode.lbd = lNode.lbd;
            segNode.rbd = rNode.rbd;
            segNode.len = lNode.len + rNode.len;
            segNode.lines = lNode.lines + rNode.lines - lNode.rbd * rNode.lbd;
        }
    }

    public int len() {
        return this.segNodes[0].len;
    }

    public int lines() {
        return this.segNodes[0].lines;
    }
}

/**
 *
 */
class SegNode {
    public int l;  //区间左边界
    public int r;  //区间右边界
    public int lbd;  //区间左边界是否填充了线段
    public int rbd;  //区间右边界是否填充了线段
    public int len;  //区间内线段总长度
    public int lines;  //区间内线段个数
    public int mark;  //区间是否被线段填充满

    public void clear() {
        this.lbd = this.rbd = this.len = this.lines = this.mark = 0;
    }
}
