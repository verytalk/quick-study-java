package com.quickstudy.api.admin.entity.auth;

import lombok.Data;

/**
 * 用户角色对应表
 * @author Jason
 */
@Data
public class AuthRoleAdmin {
  private Long id;
  private Long roleId;
  private Long adminId;

}
