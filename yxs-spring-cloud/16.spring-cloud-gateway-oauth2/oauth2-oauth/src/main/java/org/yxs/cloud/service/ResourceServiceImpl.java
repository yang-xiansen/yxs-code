package org.yxs.cloud.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.yxs.cloud.constant.RedisConstant;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
* @Description: 资源与角色匹配关系管理业务类
* @Author: y-xs
* @Date: 2020/10/12 15:10
*/
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("ADMIN", "TEST"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
