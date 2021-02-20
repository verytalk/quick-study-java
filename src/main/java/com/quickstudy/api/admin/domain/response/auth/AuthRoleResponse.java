package com.quickstudy.api.admin.domain.response.auth;

import lombok.Data;

/**
 * 角色视图
 * @author Jason
 */
@Data
public class AuthRoleResponse {

    private Long id;
    private String name;
    private Long pid;
    private Long status;
    private String remark;
    private Long listorder;

}
