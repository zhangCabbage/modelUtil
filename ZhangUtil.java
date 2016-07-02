package zhang.algorithm.modelUtil;

/**
 * Created by zhang_zack on 16/5/26.
 */
public class ZhangUtil {
    private static long startTime = 0;

    /**
     * 设置起始时间
     */
    public static void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * 获取中间间隔时间
     *
     * @return
     */
    public static long getIntervalTime() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

    }
}
