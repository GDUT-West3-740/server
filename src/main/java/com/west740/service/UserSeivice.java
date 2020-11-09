package com.west740.service;

import com.west740.bean.Role;
import com.west740.bean.User;
import com.west740.mapper.RoleMapper;
import com.west740.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 */
@Service
public class UserSeivice {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    public User loadUserByUsername(String username){
        User user = userMapper.loadUserByUsername(username);
        if(null==user){
            return new User();
        }
        List<Role> roleList = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roleList);
        return user;
    }




}
