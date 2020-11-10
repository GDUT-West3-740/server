package com.west740.mapper;

import com.west740.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lucifer
 */
@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    User getUserById(Long id);

    long register(User user);

    int deleteUserById(Long id);


    int updateUserEmail(@Param("email") String email,@Param("id") Long id);
}
