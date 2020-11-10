package com.west740.utils;

import com.west740.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lucifer
 */
public class Util {
    public static User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
