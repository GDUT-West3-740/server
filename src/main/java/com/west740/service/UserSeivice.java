package com.west740.service;

import com.west740.bean.Role;
import com.west740.bean.User;
import com.west740.mapper.RoleMapper;
import com.west740.mapper.UserMapper;
import com.west740.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lucifer
 */
@Service
public class UserSeivice implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if(null==user){
            return new User();
        }
        List<Role> roleList = roleMapper.getRolesByUid(user.getId());
        user.setRoles(roleList);
        return user;
    }

    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
    }


    public int register(User user) {
        User getUserByUsername = userMapper.loadUserByUsername(user.getUsername());
        if(getUserByUsername!=null){
            return 1;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        long result = userMapper.register(user);
        String[] roles = new String[]{"2"};
        int res = roleMapper.addRoles(roles, user.getId());
        boolean b = res == roles.length && result == 1;
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }
}
