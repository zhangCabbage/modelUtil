package zhang.algorithm.modelUtil.List;

/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/7
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class CloneList {
    /**
     * 特殊类
     */
    public static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 一定要细心, 当程序出现bug时, 要耐下心来
     *
     * @param pHead
     * @return
     */
    public static RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) return null;

        //1) 复制链表节点
        RandomListNode tmp = pHead;
        while (pHead != null) {
            RandomListNode copy = new RandomListNode(pHead.label);
            copy.next = pHead.next;
            pHead.next = copy;
            pHead = copy.next;
        }

        //2) 拼接random节点位置，注意这里不能边拼接边删除，因为可能random节点为前置节点
        RandomListNode tmpHead = tmp; //这里写成了pHead 导致出现问题
        RandomListNode copyHead = tmp.next;
        while (tmp != null) {
            RandomListNode copy = tmp.next;
            if (tmp.random != null) {
                copy.random = tmp.random.next;
            }
            tmp = copy.next;
        }

        //3) 拆开整个链表
        RandomListNode newHead = copyHead;
        while (tmpHead != null) {
            tmpHead.next = copyHead.next;
            tmpHead = tmpHead.next;
            copyHead.next = copyHead.next == null ? null : copyHead.next.next;
            copyHead = copyHead.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(1);
        RandomListNode one = new RandomListNode(2);
        head.next = one;
        Clone(head);
    }


}
