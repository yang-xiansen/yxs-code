package org.yxs.shiro.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yxs.shiro.entity.User;

@Mapper
public interface UserMapper {
    User findByUserName(@Param("userName") String userName);
}
