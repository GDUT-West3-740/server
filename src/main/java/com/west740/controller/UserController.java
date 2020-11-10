package com.west740.controller;

import com.west740.bean.ResponseBean;
import com.west740.service.UserSeivice;
import com.west740.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author lucifer
 */
public class UserController {

    @Autowired
    UserSeivice userSeivice;

    @RequestMapping("/currentUserName")
    public String getCurrentUserName(){
        return Util.getCurrentUser().getNickname();
    }

    @RequestMapping("/currentUserId")
    public Long getCurrentUserId(){
        return Util.getCurrentUser().getId();
    }

    @RequestMapping("/currentUserEmail")
    public String getCurrentUserEmail(){
        return Util.getCurrentUser().getEmail();
    }

    @RequestMapping("/isAdmin")
    public Boolean isAdmin(){
        List<GrantedAuthority> authorityList = Util.getCurrentUser().getAuthorities();
        for(GrantedAuthority authority:authorityList){
            if (authority.getAuthority().contains("超级管理员")){
                return true;
            }
        }
        return false;
    }

    @PutMapping("/updateUserEmail")
    public ResponseBean updateUserEmail(String email){
        if(userSeivice.updateUserEmail(email)==1){
            return new ResponseBean("success","开启成功！");
        }
        return new ResponseBean("error","开启失败");
    }
}
