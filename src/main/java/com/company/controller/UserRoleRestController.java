package com.company.controller;

import com.company.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/userrole")
public class UserRoleRestController {

    private final UserRoleService userRoleService;

    public UserRoleRestController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


}
