package com.quickstudy.api.admin.service.auth;


import com.quickstudy.api.admin.entity.auth.AuthPermissionRule;

import java.util.List;

/**
 * @author Jason
 */
public interface AuthPermissionRuleService {


    /**
     * listByIdIn
     * @param ids List<Long>
     * @return List<AuthPermissionRule>
     */
    List<AuthPermissionRule> listByIdIn(List<Long> ids);


    /**
     * listByPid
     * @param pid Long
     * @return List<AuthPermissionRule>
     */
    List<AuthPermissionRule> listByPid(Long pid);

    /**
     * listAll
     * @return List<AuthPermissionRule>
     */
    List<AuthPermissionRule> listAll();

    /**
     * insertAuthPermissionRule
     * @param authPermissionRule AuthPermissionRule
     * @return boolean
     */
    boolean insertAuthPermissionRule(AuthPermissionRule authPermissionRule);

    /**
     * updateAuthPermissionRule
     * @param authPermissionRule AuthPermissionRule
     * @return boolean
     */
    boolean updateAuthPermissionRule(AuthPermissionRule authPermissionRule);

    /**
     * deleteById
     * @param id Long
     * @return boolean
     */
    boolean deleteById(Long id);


}
