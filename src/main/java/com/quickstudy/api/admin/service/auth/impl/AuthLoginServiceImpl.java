package com.quickstudy.api.admin.service.auth.impl;

import com.quickstudy.api.admin.entity.auth.AuthPermission;
import com.quickstudy.api.admin.entity.auth.AuthPermissionRule;
import com.quickstudy.api.admin.entity.auth.AuthRoleAdmin;
import com.quickstudy.api.admin.service.auth.AuthRoleAdminService;
import com.quickstudy.api.admin.common.constant.CacheConstant;
import com.quickstudy.api.admin.common.util.CacheUtils;
import com.quickstudy.api.admin.service.auth.AuthLoginService;
import com.quickstudy.api.admin.service.auth.AuthPermissionRuleService;
import com.quickstudy.api.admin.service.auth.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jason
 */
@Service
@Slf4j
public class AuthLoginServiceImpl implements AuthLoginService {

    @Resource
    private AuthRoleAdminService authRoleAdminService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private AuthPermissionRuleService authPermissionRuleService;


    /**
     * 根据 管理员id 获取权限
     * @param adminId
     * @return
     */
    @Override
    public List<String> listRuleByAdminId(Long adminId) {

        List<String> authRules = new ArrayList<>();
        // 超级管理员
        if (adminId.equals(1L)) {
            authRules.add("admin");
            return authRules;
        }

        // 如果存在，先从缓存中获取权限
        String aarKey = String.format(CacheConstant.ADMIN_AUTH_RULES, adminId);

        if (CacheUtils.hasKey(aarKey)) {

            return new ArrayList<>(CacheUtils.sGetMembers(aarKey));
        }
        log.info("开始获取数据库中的用户的权限规则列表");

        // 获取角色ids
        List<AuthRoleAdmin> authRoleAdmins = authRoleAdminService.listByAdminId(adminId);

        List<Long> roleIds = authRoleAdmins.stream().map(AuthRoleAdmin::getRoleId).collect(Collectors.toList());

        // 角色授权列表
        List<AuthPermission> authPermissions = authPermissionService.listByRoleIdIn(roleIds);
        List<Long> permissionRuleIds  = authPermissions.stream().map(AuthPermission::getPermissionRuleId).collect(Collectors.toList());

        // 获取授权的规则
        List<AuthPermissionRule> authPermissionRules = authPermissionRuleService.listByIdIn(permissionRuleIds);

        // 获取权限列表
        authRules = authPermissionRules.stream().map(AuthPermissionRule::getName).collect(Collectors.toList());

        // 如果为空，则添加一个空值
        if (authRules.isEmpty()) {
            authRules.add("");
        }

        String[] strings = authRules.toArray(new String[0]);
        CacheUtils.sAdd(aarKey, strings);
        CacheUtils.expire(aarKey, 7200L); // 两小时后过期

        return authRules;
    }

}
