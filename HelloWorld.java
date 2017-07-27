package zhang.algorithm.modelUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by IntelliJ IDEA.
 * User: jiahua_MacPro
 * Date: 17/7/14
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        char[] c = "aab".toCharArray();
        System.out.println(String.valueOf(c, 1, 2));

        HelloWorld test = new HelloWorld();
        int[] nums = {3,0,1,0};
        int k = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        sb.append(4);
        sb.insert(3, 0);
        System.out.println(sb);
    }

}
