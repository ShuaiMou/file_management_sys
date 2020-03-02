package exception;

/**
 * @Auther: Saul
 * @Date: 2/3/20 9:44 下午
 * @Description:业务异常类
 */

public class BusinessException extends RuntimeException{
    private int code;
    private String msg;

    public BusinessException(){}

    public BusinessException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
