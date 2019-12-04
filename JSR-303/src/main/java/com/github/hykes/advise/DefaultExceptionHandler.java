package com.github.hykes.advise;

import com.github.hykes.common.Response;
import com.google.common.base.Throwables;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 *
 * @author hehaiyangwork@gmail.com
 * @date 2019-07-31 19:51:00
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    private static final String DUBBO_TIMEOUT_EXCEPTION = "Invoke remote method timeout.";

    @Autowired
    private MessageSource messageSource;

    /**
     * Response 封装异常
     *
     * @param response http 响应
     * @param e 异常
     * @return Response 对象
     * @see Response
     */
    @ExceptionHandler(value = Exception.class)
    public Response<Object> defaultErrorHandler(HttpServletResponse response, Exception e) {
        log.error("un.expected.error:", e);
        // 异常默认返回500
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

//        if (e instanceof JsonResponseException) {
//            log.error("JsonResponseException happened, cause:{}", Throwables.getStackTraceAsString(e));
//            response.setStatus(((JsonResponseException) e).getStatus());
//            return i18n(e.getMessage());
//        }
//
//        if (e instanceof ServiceException) {
//            log.error("ServiceException happened, cause:{}", Throwables.getStackTraceAsString(e));
//            return i18n(e.getMessage());
//        }
//
//        if (e instanceof RpcException) {
//            String message = e.getMessage();
//            if (!StringUtils.isEmpty(message) && message.contains(DUBBO_TIMEOUT_EXCEPTION)) {
//                return Response.fail("服务调用超时。");
//            }
//            return Response.fail("服务调用异常。");
//        }
        return Response.fail("系统异常");
    }

    /**
     * 业务异常处理
     *
     * @param originMessage 异常消息
     * @return 响应结果
     */
    private Response<Object> i18n(String originMessage) {
        Locale locale = new Locale("zh", "CN");
        String message = messageSource.getMessage(originMessage, null, originMessage, locale);
        return Response.fail(message);
    }

    @ExceptionHandler(NullPointerException.class)
    public void onNPE(NullPointerException e, HttpServletResponse response) throws Exception {
        log.error("NPE happened, cause:{}", Throwables.getStackTraceAsString(e));
        Locale locale = new Locale("zh", "CN");
        String message = messageSource.getMessage("npe.error", null, "npe.error", locale);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handle(MethodArgumentNotValidException exception, HttpServletRequest request) {
        StringBuffer errorInfo=new StringBuffer();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for(int i=0;i<errors.size();i++){
            errorInfo.append(errors.get(i).getDefaultMessage()+",");
        }
        log.error("{},接口参数验证失败：{}",request,errorInfo.toString());
        return "参数验证失败，失败信息如下:"+errorInfo.toString();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ConstraintViolationException exception, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuffer errorInfo = new StringBuffer();
        for (ConstraintViolation<?> item : violations) {
            /**打印验证不通过的信息*/
            errorInfo.append(item.getMessage());
            errorInfo.append(",");
        }
        log.error("{}接口参数验证失败，内容如下：{}",request.getRequestURI(),errorInfo.toString());
        return "参数验证失败，失败信息如下："+ errorInfo.toString();
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException e){

        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        StringBuffer errorMsg = new StringBuffer();

        errors.forEach(x->errorMsg.append(x.getDefaultMessage()).append(";"));

        return "参数验证失败，失败信息如下："+ errorMsg.toString();

    }

    //接收参数需配@Requestparam
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String test(MissingServletRequestParameterException ex){
        return "参数验证失败，失败信息如下："+ ex.getMessage();
    }



}
