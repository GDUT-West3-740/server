package com.west740.controller;

import com.west740.bean.ResponseBean;
import com.west740.bean.User;
import com.west740.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucifer
 */
@RestController
public class LoginRegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/login_error")
    public ResponseBean loginError(){
        return new ResponseBean("error","登录失败！");
    }

    @RequestMapping("/logib_success")
    public ResponseBean loginSuccess(){
        return new ResponseBean("success","登录成功！");
    }

    @RequestMapping("/login_page")
    public ResponseBean loginPage(){
        return new ResponseBean("error","尚未登录，请登录！");
    }

    @PostMapping("/reg")
    public ResponseBean register(User user){
        int result = userService.register(user);
        if (result == 0){
            return new ResponseBean("success","注册成功！");
        }else if (result == 1){
            return new ResponseBean("error","用户名重复，注册失败！");
        }else {
            return new ResponseBean("error","注册失败！");
        }
    }

}
