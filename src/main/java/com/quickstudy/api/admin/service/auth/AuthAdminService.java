package com.quickstudy.api.admin.service.auth;


import com.quickstudy.api.admin.entity.auth.AuthAdmin;
import com.quickstudy.api.admin.domain.request.auth.AuthAdminQueryRequest;

import java.util.List;

/**
 * @author Jason
 */
public interface AuthAdminService {

    /**
     * listAdminPage
     * @param authAdminQueryRequest
     * @return
     */
    List<AuthAdmin> listAdminPage(AuthAdminQueryRequest authAdminQueryRequest);

    /**
     * findByUserName
     * @param userName
     * @return
     */
    AuthAdmin findByUserName(String userName);

    /**
     * findById
     * @param id
     * @return
     */
    AuthAdmin findById(Long id);


    /**
     * findPwdById
     * @param id
     * @return
     */
    AuthAdmin findPwdById(Long id);

    /**
     * insertAuthAdmin
     * @param authAdmin
     * @return
     */
    boolean insertAuthAdmin(AuthAdmin authAdmin);

    /**
     * updateAuthAdmin
     * @param authAdmin
     * @return
     */
    boolean updateAuthAdmin(AuthAdmin authAdmin);

    /**
     * deleteById
     * @param id
     * @return
     */
    boolean deleteById(Long id);

}
