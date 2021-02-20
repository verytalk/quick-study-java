package com.quickstudy.api.admin.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.quickstudy.api.admin.dao.auth.AuthRoleDao;
import com.quickstudy.api.admin.entity.auth.AuthRole;
import com.quickstudy.api.admin.domain.request.auth.AuthRoleQueryRequest;
import com.quickstudy.api.admin.service.auth.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Resource
    private AuthRoleDao authRoleDao;

    /**
     * 查询列表
     * @return List<AuthRole>
     */
    @Override
    public List<AuthRole> listAdminPage(AuthRoleQueryRequest authRoleQueryRequest) {
        int offset = (authRoleQueryRequest.getPage() - 1) * authRoleQueryRequest.getLimit();
        PageHelper.offsetPage(offset, authRoleQueryRequest.getLimit());
        return authRoleDao.listAdminPage(authRoleQueryRequest);
    }

    /**
     * 查询管理员页面的列表
     * @param page Integer
     * @param limit Integer
     * @param status Integer
     * @return List<AuthRole>
     */
    @Override
    public List<AuthRole> listAuthAdminRolePage(Integer page, Integer limit, Integer status) {
        page = page != null && page > 0 ? page : 1;
        limit = limit != null && limit > 0 && limit < 100 ? limit : 100;
        int offset = (page - 1) * limit;
        PageHelper.offsetPage(offset, limit);
        List<AuthRole> list = authRoleDao.listAuthAdminRolePage(status);
        return list;
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public AuthRole findByName(String name) {
        return authRoleDao.findByName(name);
    }

    /**
     * 插入
     * @param authRole
     * @return
     */
    @Override
    public boolean insertAuthRole(AuthRole authRole) {

        authRole.setCreateTime(new Date());
        authRole.setUpdateTime(new Date());

        return authRoleDao.insertAuthRole(authRole);
    }

    /**
     * 修改
     * @param authRole
     * @return
     */
    @Override
    public boolean updateAuthRole(AuthRole authRole) {
        authRole.setUpdateTime(new Date());
        return authRoleDao.updateAuthRole(authRole);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        return authRoleDao.deleteById(id);
    }
}
