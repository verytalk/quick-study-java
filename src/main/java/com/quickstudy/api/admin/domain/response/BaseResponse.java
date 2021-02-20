package com.quickstudy.api.admin.domain.response;

import lombok.Data;

/**
 * 返回结果类
 * @author Jason
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;
}
