package com.quickstudy.api.admin.domain.request;

import lombok.Data;

import java.util.UUID;

/**
 * @author Jason
 */
@Data
public class BaseRequest {

    /**
     * 唯一请求号
     */
    private String reqNo;

    /**
     * 请求的时间戳
     */
    private Long timeStamp;

    public BaseRequest() {
        this.reqNo = UUID.randomUUID().toString();
        this.timeStamp = System.currentTimeMillis();
    }

}
