package com.quickstudy.api.admin.service.auth;


import com.quickstudy.api.admin.entity.auth.AuthRoleAdmin;

import java.util.List;

/**
 * @author Jason
 */
public interface AuthRoleAdminService {

    List<AuthRoleAdmin> listByAdminId(Long adminId);

    List<AuthRoleAdmin> listByAdminIdIn(List<Long> adminIds);

    List<AuthRoleAdmin> listByRoleId(Long roleId);

    int insertAuthRoleAdminAll(List<AuthRoleAdmin> authRoleAdminList);

    int insertRolesAdminIdAll(List<Long> roles, Long adminId);

    boolean deleteByAdminId(Long adminId);

}
