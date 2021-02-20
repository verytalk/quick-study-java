package com.quickstudy.api.admin.controller;

import com.quickstudy.api.admin.service.auth.AuthAdminService;
import com.quickstudy.api.admin.common.util.ResultVOUtils;
import com.quickstudy.api.admin.domain.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jason
 */
@RestController
public class HelloController {

    @Autowired
    private AuthAdminService authAdminService;

    @GetMapping("/hello")
    public BaseResponse hello(@RequestParam(value = "offset") Integer offset, @RequestParam("offset") Integer limit) {
        return ResultVOUtils.error(1, "test");
    }

}
