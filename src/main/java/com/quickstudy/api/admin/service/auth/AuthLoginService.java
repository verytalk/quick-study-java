package com.quickstudy.api.admin.service.auth;

import java.util.List;


/**
 * @author Jason
 */
public interface AuthLoginService {

    /**
     * listRuleByAdminId
     * @param adminId Long
     * @return List<String>
     */
    List<String> listRuleByAdminId(Long adminId);

}
