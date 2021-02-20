package com.quickstudy.api.admin.controller.auth;

import com.quickstudy.api.admin.common.aspect.annotation.AuthRuleAnnotation;
import com.quickstudy.api.admin.entity.auth.AuthAdmin;
import com.quickstudy.api.admin.common.enums.ResultEnum;
import com.quickstudy.api.admin.common.exception.JsonException;
import com.quickstudy.api.admin.domain.request.auth.LoginRequest;
import com.quickstudy.api.admin.domain.request.auth.UpdatePasswordRequest;
import com.quickstudy.api.admin.service.auth.AuthAdminService;
import com.quickstudy.api.admin.service.auth.AuthLoginService;
import com.quickstudy.api.admin.common.util.PasswordUtils;
import com.quickstudy.api.admin.domain.response.auth.LoginUserInfoResponse;
import com.quickstudy.api.admin.common.util.IpUtils;
import com.quickstudy.api.admin.common.util.JwtUtils;
import com.quickstudy.api.admin.common.util.ResultVOUtils;
import com.quickstudy.api.admin.domain.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * 登录相关
 * @author Jason
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private AuthLoginService authLoginService;

    @Autowired
    private AuthAdminService authAdminService;

    /**
     * 用户登录
     * @return BaseResponse
     */
    @PostMapping(value = "/admin/auth/login/index")
    public BaseResponse index(@RequestBody @Valid LoginRequest loginRequest,
                              BindingResult bindingResult,
                              HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findByUserName(loginRequest.getUserName());
        if (authAdmin == null) {
            throw new JsonException(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        if (!PasswordUtils.authAdminPwd(loginRequest.getPwd()).equals(authAdmin.getPassword())) {
            throw new JsonException(ResultEnum.DATA_NOT, "用户名或密码错误");
        }

        // 更新登录状态
        AuthAdmin authAdminUp = new AuthAdmin();
        authAdminUp.setId(authAdmin.getId());
        authAdminUp.setLastLoginTime(new Date());
        authAdminUp.setLastLoginIp(IpUtils.getIpAddr(request));
        authAdminService.updateAuthAdmin(authAdminUp);

        // 登录成功后获取权限，这里面会设置到缓存
        authLoginService.listRuleByAdminId(authAdmin.getId());

        Map<String, Object> claims = new HashMap<>(16);
        claims.put("admin_id", authAdmin.getId());
        // 一天后过期
        String token = JwtUtils.createToken(claims, 86400L);
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", authAdmin.getId());
        map.put("token", token);

        return ResultVOUtils.success(map);
    }

    /**
     * 获取登录用户信息
     * @return
     */
    @AuthRuleAnnotation("")
    @GetMapping("/admin/auth/login/userInfo")
    public BaseResponse userInfo(HttpServletRequest request) {
        String adminId = request.getHeader("X-Adminid");
        Long id = Long.valueOf(adminId);

        AuthAdmin authAdmin = authAdminService.findById(id);

        List<String> authRules = authLoginService.listRuleByAdminId(authAdmin.getId());

        LoginUserInfoResponse loginUserInfoResponse = new LoginUserInfoResponse();
        BeanUtils.copyProperties(authAdmin, loginUserInfoResponse);
        loginUserInfoResponse.setAuthRules(authRules);

        return ResultVOUtils.success(loginUserInfoResponse);
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/admin/auth/login/out")
    public BaseResponse out(){
        return ResultVOUtils.success();
    }

    /**
     * 修改密码
     * @return
     */
    @AuthRuleAnnotation("") // 需要登录验证,但是不需要权限验证时,value 值填空字符串
    @PostMapping("/admin/auth/login/password")
    public BaseResponse password(@RequestBody @Valid UpdatePasswordRequest updatePasswordRequest,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        AuthAdmin authAdmin = authAdminService.findPwdById(updatePasswordRequest.getAdminId());
        if (authAdmin == null) {
            throw new JsonException(ResultEnum.DATA_NOT);
        }
        String oldPwd = PasswordUtils.authAdminPwd(updatePasswordRequest.getOldPassword());
        // 旧密码不对
        if (authAdmin.getPassword() != null
                && !authAdmin.getPassword().equals(oldPwd)) {
            throw new JsonException(ResultEnum.DATA_NOT, "旧密码匹配失败");
        }

        AuthAdmin authAdminUp = new AuthAdmin();
        authAdminUp.setId(authAdmin.getId());
        String newPwd = PasswordUtils.authAdminPwd(updatePasswordRequest.getNewPassword());
        authAdminUp.setPassword(newPwd);

        boolean b = authAdminService.updateAuthAdmin(authAdminUp);
        if (b) {
            return ResultVOUtils.success();
        }

        return ResultVOUtils.error(ResultEnum.DATA_CHANGE);
    }

}
