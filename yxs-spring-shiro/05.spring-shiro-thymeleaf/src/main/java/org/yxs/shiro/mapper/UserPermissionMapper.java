package org.yxs.shiro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.yxs.shiro.entity.Permission;

@Mapper
public interface UserPermissionMapper {

	List<Permission> findByUserName(String userName);
}
