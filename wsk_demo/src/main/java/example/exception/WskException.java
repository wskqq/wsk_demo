package example.exception;

/**
 * @Description 异常处理类
 * @Author acer
 * @Date 2022/3/4 17:23
 * @Version 1.0
 */
public class WskException extends Exception {

    public WskException(String message) {
        super(message);
    }

    public WskException(String message, Throwable cause) {
        super(message, cause);
    }

    public WskException(Throwable cause) {
        super(cause);
    }

}
