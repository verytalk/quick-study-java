package com.quickstudy.api.admin.service.auth.impl;

import com.quickstudy.api.admin.dao.auth.AuthPermissionDao;
import com.quickstudy.api.admin.entity.auth.AuthPermission;
import com.quickstudy.api.admin.service.auth.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AuthPermissionServiceImpl implements AuthPermissionService {

    @Resource
    private AuthPermissionDao authPermissionDao;

    /**
     * 根据 多个角色id 查询
     * @param roleIds
     * @return
     */
    @Override
    public List<AuthPermission> listByRoleIdIn(List<Long> roleIds) {
        if (roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return authPermissionDao.listByRoleIdIn(roleIds);
    }

    /**
     * 根据某个角色id 查询
     * @param roleId
     * @return
     */
    @Override
    public List<AuthPermission> listByRoleId(Long roleId) {
        return authPermissionDao.listByRoleId(roleId);
    }

    /**
     * 批量插入
     * @param authPermissionList
     * @return
     */
    @Override
    public int insertAuthPermissionAll(List<AuthPermission> authPermissionList) {
        return authPermissionDao.insertAuthPermissionAll(authPermissionList);
    }

    /**
     * 根据角色id删除
     * @param roleId
     */
    @Override
    public void deleteByRoleId(Long roleId) {
        authPermissionDao.deleteByRoleId(roleId);
    }
}
