package com.yangblog.core.dao;

import com.yangblog.core.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insert(User record);

    int updateByPrimaryKey(User record);

    User selectByPrimaryKey(Integer id);

    int deleteByPrimaryKey(Integer id);

    List<User> selectAll();

    User selectByToken(String token);

    User selectByAccount(String account);

    int updateToken(Integer id, String token);

}