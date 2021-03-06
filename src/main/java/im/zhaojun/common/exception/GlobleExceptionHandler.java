package im.zhaojun.common.exception;

import im.zhaojun.common.model.dto.ResultBean;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理器
 * @author zhaojun
 */
@ControllerAdvice
public class GlobleExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobleExceptionHandler.class);

    @ExceptionHandler(SearchDisableException.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultBean searchDisableExceptionHandler(SearchDisableException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return ResultBean.error(e.getMessage());
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus
    public ResultBean searchDisableExceptionHandler(StorageStrategyUninitializedException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return ResultBean.error(e.getMessage());
    }


    /**
     * 不存在的文件异常
     */
    @ExceptionHandler({NotExistFileException.class})
    @ResponseBody
    public ResultBean notExistFile(Exception ex) {
        return ResultBean.error("文件不存在");
    }

    /**
     * 捕获 ClientAbortException 异常, 不做任何处理, 防止出现大量堆栈日志输出, 此异常不影响功能.
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class, ClientAbortException.class})
    @ResponseBody
    @ResponseStatus
    public void clientAbortException(Exception ex) {
        // if (log.isDebugEnabled()) {
        //     log.debug("出现了断开异常:", ex);
        // }
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultBean searchDisableExceptionHandler(Exception e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return ResultBean.error("系统异常, 请联系管理员");
    }

}
