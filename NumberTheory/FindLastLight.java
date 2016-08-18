package zhang.algorithm.modelUtil.NumberTheory;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_MacPro
 * Date: 16/8/11
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 * <p>
 * 1、100盏灯找最后亮着的灯(http://www.cnblogs.com/dhf327/p/4773672.html)
 * 把问题最后转化为100个数中, 因数个数为奇数的数, 可以知道只有当此数为平方数其因数个数才能为奇数
 *
 * 2、python高级编程群的入群算法题, 而作者的分析二中以下两个定理却没什么卵用
 *
 * [约数个数定理]
 * 对于 n(n>1), 分解质因数为：n = p1^a1 * p2^a2 * p3^a3 * … * pk^ak
 * 则由约数个数定理可知n的正约数有 (a₁+1)(a₂+1)(a₃+1)…(ak+1) 个
 * [约数和定理]
 * 对于以上这么多约数, 其和为
 * f(n) = (p1^0+p1^1+p1^2+…p1^a1) * (p2^0+p2^1+p2^2+…p2^a2) * … * (pk^0+pk^1+pk^2+…pk^ak）
 *
 *
 */
public class FindLastLight {
    public void findlastLight(int num) {
        int sqrt = (int) Math.sqrt(num);//亮灯个数
        for (int i = 1; i <= sqrt; i++) {
            System.out.println(i * i);//亮灯编号
        }
    }
}
