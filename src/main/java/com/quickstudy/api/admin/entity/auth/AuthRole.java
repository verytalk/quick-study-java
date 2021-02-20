package com.quickstudy.api.admin.entity.auth;

import lombok.Data;

import java.util.Date;

/**
 * 角色表
 * @author Jason
 */
@Data
public class AuthRole {

  private Long id;
  private String name;
  private Long pid;
  private Long status;
  private String remark;
  private Long listorder;
  private Date createTime;
  private Date updateTime;
}
