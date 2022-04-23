package com.yangblog.web.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yangblog.core.service.UserService;
import com.yangblog.utils.webapp.WebAppApiResponse;
import com.yangblog.utils.webapp.WebappBaseApiController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/user")
public class AdminUserController extends WebappBaseApiController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public WebAppApiResponse login(@RequestBody JsonNode node) {
        String mobile = getRequiredTextField(node, "mobile");
        String password = getRequiredTextField(node, "password");
        Map<String, Object> map = userService.adminLogin(mobile, password);
        return new WebAppApiResponse(map);
    }

}
