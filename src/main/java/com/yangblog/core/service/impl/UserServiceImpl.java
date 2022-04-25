package com.yangblog.core.service.impl;

import com.yangblog.core.dao.UserMapper;
import com.yangblog.core.domain.User;
import com.yangblog.core.domain.view.UserView;
import com.yangblog.core.service.UserService;
import com.yangblog.utils.PasswordUtils;
import com.yangblog.utils.webapp.WebappRetCode;
import com.yangblog.utils.webapp.WebappServiceException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Integer touch(String token) {
        User user = userMapper.selectByToken(token);
        return Objects.nonNull(user) ? user.getId() : null;
    }

    @Override
    public Map<String, Object> adminLogin(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.selectByAccount(account);
        if (Objects.isNull(user) || !user.getPassword().equals(PasswordUtils.encrypt(password))) {
            throw new WebappServiceException(WebappRetCode.CUSTOM_ERROR, "账号不存在或密码错误");
        }
        UserView userView = UserView.builder()
                .id(user.getId())
                .account(user.getAccount())
                .address(user.getAddress())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .name(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .token(RandomStringUtils.randomAlphabetic(24))
                .build();
        userMapper.updateToken(user.getId(), userView.getToken());
        map.put("user", userView);
        return map;
    }


}
