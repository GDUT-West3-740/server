package com.west740.mapper;

import com.west740.bean.Role;
import com.west740.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
public interface UserMapper {


    User getUserById(@Param("id") Long id);

    long register(User user);

    int deleteUserById(Long id);

    User loadUserByUsername(@Param("username") String username);

    int updateUserEmail(@Param("email") String email, @Param("id") Long id);

    List<User> getUserByNickname(@Param("nickname") String nickname);

    List<Role> getAllRole();

    int deleteUserRolesByUid(Long id);

    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);

    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);




}
