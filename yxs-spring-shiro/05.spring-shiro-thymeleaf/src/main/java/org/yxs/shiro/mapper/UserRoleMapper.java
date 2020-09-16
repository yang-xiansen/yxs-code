package org.yxs.shiro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.yxs.shiro.entity.Role;

@Mapper
public interface UserRoleMapper {

	List<Role> findByUserName(String userName);
}
