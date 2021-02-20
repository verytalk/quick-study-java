package com.quickstudy.api.admin.domain.response;

import lombok.Data;

import java.util.List;

/**
 * 简单的分页返回对象
 */
@SuppressWarnings("ALL")
@Data
public class PageSimpleResponse<T> {
    // 总数
    private Long total;
    // 列表
    private List<T> list;
}
