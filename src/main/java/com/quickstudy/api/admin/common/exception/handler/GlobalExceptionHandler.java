package com.quickstudy.api.admin.common.exception.handler;

import com.quickstudy.api.admin.common.enums.ResultEnum;
import com.quickstudy.api.admin.common.exception.JsonException;
import com.quickstudy.api.admin.common.util.ResultVOUtils;
import com.quickstudy.api.admin.domain.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 错误回调
 * @author Jason
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * // 拦截API异常
     * @param e
     * @return BaseResponse
     */
    @ExceptionHandler(value = JsonException.class)
    public BaseResponse handlerJsonException(JsonException e) {
        // 返回对应的错误信息
        return ResultVOUtils.error(e.getCode(), e.getMessage());
    }
    /**
     *   // 拦截API异常
     * @return BaseResponse
     */
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse handlerRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        // 返回对应的错误信息
        return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
    }

}
