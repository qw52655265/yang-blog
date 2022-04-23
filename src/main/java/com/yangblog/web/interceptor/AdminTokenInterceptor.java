package com.yangblog.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangblog.core.service.UserService;
import com.yangblog.utils.webapp.WebappRetCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class AdminTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        log.debug("url={}, token={}", request.getRequestURI(), token);

        if (!StringUtils.hasText(token)) {
            Integer editorId = userService.touch(token);
            if (Objects.nonNull(editorId)) {
                request.setAttribute("userId", editorId.toString());
                return true;
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        body.put("code", WebappRetCode.FORBIDDEN.getCode());
        body.put("message", "");
        body.put("data", data);
        response.addHeader("Content-Type", "application/json;charset=UTF8");
        response.getWriter().println(mapper.writeValueAsString(body));
        return false;
    }

}
