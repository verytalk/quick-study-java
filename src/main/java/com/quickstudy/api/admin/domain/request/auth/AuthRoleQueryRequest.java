package com.quickstudy.api.admin.domain.request.auth;

import com.quickstudy.api.admin.domain.request.ListPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色的查询表单
 * @author Jason
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthRoleQueryRequest extends ListPageRequest {
    private String name;
    private Integer status;

}
