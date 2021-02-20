package com.quickstudy.api.admin.service.auth;


import com.quickstudy.api.admin.entity.auth.AuthPermission;

import java.util.List;

/**
 * @author Jason
 */
public interface AuthPermissionService {

    List<AuthPermission> listByRoleIdIn(List<Long> roleIds);

    List<AuthPermission> listByRoleId(Long roleId);

    int insertAuthPermissionAll(List<AuthPermission> authPermissionList);

    void deleteByRoleId(Long roleId);

}
