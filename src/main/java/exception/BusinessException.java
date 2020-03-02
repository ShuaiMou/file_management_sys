package exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Auther: Saul
 * @Date: 2/3/20 9:44 下午
 * @Description:业务异常类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException{
    private int code;
    private String msg;
}
