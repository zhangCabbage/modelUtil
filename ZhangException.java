package zhang.algorithm.modelUtil;

/**
 * Created by IntelliJ IDEA.
 * User: zhang_Lenovo
 * Date: 2016/6/23
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public class ZhangException extends Exception {
    public ZhangException(){
        super();
    }

    public ZhangException(String message) {
        super(message);
    }

    public ZhangException(Throwable cause) {
        super(cause);
    }

    public ZhangException(String message, Throwable cause) {
        super(message, cause);
    }
}
