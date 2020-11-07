package com.west740.mapper;

import com.west740.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lucifer
 */
@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    User getUserById(Long id);

    long register(User user);

    int deleteUserById(Long id);



}
