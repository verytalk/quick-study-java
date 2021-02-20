package com.quickstudy.api.admin.common.util;

import com.quickstudy.api.admin.entity.auth.AuthPermissionRule;
import com.quickstudy.api.admin.domain.response.auth.AuthPermissionRuleMergeResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限规则生成树形节点工具类
 */
@SuppressWarnings("ALL")
public class PermissionRuleTreeUtils {

    /**
     * 多维数组
     */
    public static List<AuthPermissionRuleMergeResponse> merge(List<AuthPermissionRule> authPermissionRuleList,
                                                              Long pid) {

        List<AuthPermissionRuleMergeResponse> authPermissionRuleMergeResponseList = new ArrayList<>();
        for (AuthPermissionRule v : authPermissionRuleList) {
            AuthPermissionRuleMergeResponse authPermissionRuleMergeResponse = new AuthPermissionRuleMergeResponse();
            BeanUtils.copyProperties(v, authPermissionRuleMergeResponse);
            if (pid.equals(v.getPid())) {
                authPermissionRuleMergeResponse.setChildren(merge(authPermissionRuleList, v.getId()));
                authPermissionRuleMergeResponseList.add(authPermissionRuleMergeResponse);
            }
        }

        return authPermissionRuleMergeResponseList;
    }



}
