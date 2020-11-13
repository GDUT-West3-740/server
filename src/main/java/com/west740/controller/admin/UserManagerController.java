package com.west740.controller.admin;

import com.west740.bean.ResponseBean;
import com.west740.bean.Role;
import com.west740.bean.User;
import com.west740.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lucifer
 */
@RequestMapping("/admin")
@RestController
public class UserManagerController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user")
    public List<User> getUserByNickname(String nickname) {
        return userService.getUserByNickname(nickname);
    }
    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/roles")
    public List<Role> getAllRole() {
        return userService.getAllRole();
    }

    @PutMapping(value = "/user/enabled")
    public ResponseBean updateUserEnabled(Boolean enabled, Long uid) {
        if (userService.updateUserEnabled(enabled, uid) == 1) {
            return new ResponseBean("success", "更新成功!");
        } else {
            return new ResponseBean("error", "更新失败!");
        }
    }

    @DeleteMapping("/user/{uid}")
    public ResponseBean deleteUserById(@PathVariable("uid") Long uid){
        if(userService.deleteUserById(uid)==1){
            return new ResponseBean("success", "删除成功!");
        } else {
            return new ResponseBean("error", "删除失败!");
        }
    }

    @PutMapping("/user/role")
    public ResponseBean updateUserRoles(Long[] rids, Long id){
        if(userService.updateUserRoles(rids, id) == rids.length){
            return new ResponseBean("success", "更新成功!");
        } else {
            return new ResponseBean("error", "更新失败!");
        }
    }

}
