package zhang.algorithm.modelUtil;

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


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start...");
                    Thread.sleep(3000);
                    System.out.println("...thread over.");
                } catch (InterruptedException e) {
                    System.out.println("Be interrupt!");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        Object
    }
}
