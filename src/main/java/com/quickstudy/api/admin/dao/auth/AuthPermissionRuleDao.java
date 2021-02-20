package com.quickstudy.api.admin.dao.auth;

import com.quickstudy.api.admin.entity.auth.AuthPermissionRule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author Jason
 */
@Mapper
public interface AuthPermissionRuleDao {

    /**
     * 根据ids查询 规则名称
     * @param ids 传入的ids
     * @return List<AuthPermissionRule>
     */
    List<AuthPermissionRule> listByIdIn(List<Long> ids);

    /**
     * 查询所有
     * @return List<AuthPermissionRule>
     */
    List<AuthPermissionRule> listAll();

    /**
     * 根据 父级 pid 查询
     * @param pid Long
     * @return List<AuthPermissionRule>
     */
    List<AuthPermissionRule> listByPid(Long pid);

    /**
     * 根据 规则名称查询
     * @param name String
     * @return AuthPermissionRule
     */
    AuthPermissionRule findByName(String name);

    /**
     * 插入
     * @param authPermissionRule AuthPermissionRule
     * @return boolean
     */
    boolean insertAuthPermissionRule(AuthPermissionRule authPermissionRule);

    /**
     * 更新
     * @param authPermissionRule AuthPermissionRule
     * @return boolean
     */
    boolean updateAuthPermissionRule(AuthPermissionRule authPermissionRule);

    /**
     * 删除
     * @param id Long
     * @return boolean
     */
    boolean deleteById(Long id);

}
