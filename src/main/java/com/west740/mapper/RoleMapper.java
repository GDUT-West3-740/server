package com.west740.mapper;

import com.west740.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author lucifer
 */
@Mapper
public interface RoleMapper {

    List<Role> getRolesByUid(Long uid);

}
