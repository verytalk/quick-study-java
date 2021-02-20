package com.quickstudy.api.admin.service.auth;


import com.quickstudy.api.admin.entity.auth.AuthRole;
import com.quickstudy.api.admin.domain.request.auth.AuthRoleQueryRequest;

import java.util.List;

/**
 * @author Jason
 */
public interface AuthRoleService {

    List<AuthRole> listAdminPage(AuthRoleQueryRequest authRoleQueryRequest);

    List<AuthRole> listAuthAdminRolePage(Integer page, Integer limit, Integer status);

    AuthRole findByName(String name);

    boolean insertAuthRole(AuthRole authRole);

    boolean updateAuthRole(AuthRole authRole);

    boolean deleteById(Long id);

}
