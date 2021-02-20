package com.quickstudy.api.admin.dao.auth;

import com.quickstudy.api.admin.entity.auth.AuthRole;
import com.quickstudy.api.admin.domain.request.auth.AuthRoleQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthRoleDao {

    /**
     * 后台管理业务查询列表
     * @param authRoleQueryRequest AuthRoleQueryRequest
     * @return 列表 List<AuthRole>
     */
    List<AuthRole> listAdminPage(AuthRoleQueryRequest authRoleQueryRequest);

    /**
     * 返回id,name 字段的列表
     * @param status
     * @return 列表
     */
    List<AuthRole> listAuthAdminRolePage(Integer status);

    /**
     * findByName
     * @param name String
     * @return AuthRole
     */
    AuthRole findByName(String name);

    /**
     * 插入
     * @param authAdmin AuthRole
     * @return boolean
     */
    boolean insertAuthRole(AuthRole authAdmin);

    /**
     * 更新
     * @param authAdmin AuthRole
     * @return boolean
     */
    boolean updateAuthRole(AuthRole authAdmin);

    /**
     * 删除
     * @param id Long
     * @return boolean
     */
    boolean deleteById(Long id);

}
