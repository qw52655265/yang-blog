package com.yangblog.core.service;

import java.util.Map;

public interface UserService {

    Integer touch(String token);

    Map<String, Object> adminLogin(String account, String password);

}
