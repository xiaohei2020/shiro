package com.baizhi;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        //根据username查询user对象
        AuthenticationInfo authenticationInfo = null;
        if (username.equals("zhangsan")) {
            authenticationInfo = new SimpleAuthenticationInfo("zhangsan", "68609b8b64988c0f4def093eaa025e05", ByteSource.Util.bytes("abcd"), this.getName());
        }
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        //用户（主体）   ---》 角色 super -----》权限  admin:delete

        SimpleAuthorizationInfo authorizationInfo = null;
        if (username.equals("zhangsan")) {
            authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRole("super");
            authorizationInfo.addStringPermission("admin:delete");
        }
        return authorizationInfo;
    }
}
