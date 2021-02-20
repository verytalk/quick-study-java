package com.quickstudy.api.admin.domain.response.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 后台管理的 管理员管理页面的 VO
 * @author Jason
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthAdminResponse {

    /**
     * 主键
     */
    private Long id;
    /**
     * 昵称
     */
    private String username;
    /**
     * 最后登录ip
     */
    private String lastLoginIp;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 角色ids
     */
    private List<Long> roles;

}
