package org.yxs.wj.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.yxs.wj.domain.entity.User;
import org.yxs.wj.exception.WJException;
import org.yxs.wj.service.PermissionService;
import org.yxs.wj.service.UserService;

import java.util.Objects;
import java.util.Set;

public class WJRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * @param principalCollection
     * @Description: 获取授权信息
     * @return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: yang-xiansen
     * @Date: 2020/08/09 9:21
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户的所有权限
        String username = principalCollection.getPrimaryPrincipal().toString();
        Set<String> permissions = permissionService.listPermissionURLsByUser(username);

        // 将权限放入授权信息中
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setStringPermissions(permissions);
        return s;
    }


    /**
     * @param authenticationToken
     * @Description: 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
     * @return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: yang-xiansen
     * @Date: 2020/08/09 9:21
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = authenticationToken.getPrincipal().toString();
        User user = userService.findByUsername(userName);
        if (Objects.isNull(user)) {
            throw new WJException("用户不存在");
        }
        String password = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes(salt), getName());
        return info;
    }
}
