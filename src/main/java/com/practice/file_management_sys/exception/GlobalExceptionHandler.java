package exception;

import com.practice.file_management_sys.domain.JsonData;
import com.practice.file_management_sys.enumClass.StateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Saul
 * @Date: 2/3/20 9:46 下午
 * @Description:异常统一处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER =  LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    /***
     * @Author Saul
     * @Description  TODO: 业务异常
     * @Date 10:18 下午 2/3/20
     * @param e
     * @return {@link {@link com.practice.file_management_sys.domain.JsonData<java.lang.Object>}}
     */
    @ExceptionHandler(value = BusinessException.class)
    public JsonData handleBusinessException(BusinessException e, HttpServletRequest request){
        LOGGER.error("url {}, code {}, msg {}",  request.getRequestURL(), e.getCode(), e.getMessage());
        return new JsonData(e.getCode(), e.getMsg());
    }


    /***
     * @Author Saul
     * @Description  TODO: 处理预期以外的异常
     * @Date 10:28 下午 2/3/20
     * @param e
     * @return {@link {@link com.practice.file_management_sys.domain.JsonData<java.lang.Object>}}
     */
    @ExceptionHandler(value = Exception.class)
    public JsonData<Object>  handleException(Exception e){
        LOGGER.error("msg {}", e.getMessage());
        return new JsonData<>(StateType.INTERNAL_SERVER_ERROR.getCode(),StateType.INTERNAL_SERVER_ERROR.value());
    }
    
    
    



}
