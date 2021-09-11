package com.zking.ssm.shiro;

import com.zking.ssm.model.User;
import com.zking.ssm.service.IUserService;
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

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    //权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = principalCollection.getPrimaryPrincipal().toString();
        Set<String> Sets = userService.getRole(userName);
        Set<String> permission = userService.getPermission(userName);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(Sets);
        info.setStringPermissions(permission);
        return info;
    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();//获取账号
        String passwrod = authenticationToken.getCredentials().toString();//获取密码

        User  user=new User();
        user.setUsername(username);
        user.setPassword(passwrod);
        User u = userService.login(user);
        if (null==u){
            throw new RuntimeException("账号不存在");
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                u.getUsername(),u.getPassword(), ByteSource.Util.bytes(u.getSalt()),this.getName());

        return authenticationInfo;
    }
}
