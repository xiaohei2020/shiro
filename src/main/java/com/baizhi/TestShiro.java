package com.baizhi;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class TestShiro {
    public static void main(String[] args) {
        //创建管理器工厂对象
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取到一个安全管理器
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //将安全管理器应用在安全工具类里
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //构建令牌
        //UnknownAccountException : 代表的是账号不存在
        //IncorrectCredentialsException ：代表的是密码有误。
        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "123456");
        //主体进行认证
        subject.login(token);
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            //boolean aSuper = subject.hasRole("super");   判断主体有没有当前这个角色
            //boolean b = subject.hasAllRoles(list);     判断主体是否有集合里面所有的角色
            //boolean[] booleans = subject.hasRoles(list);  判断主体是否有集合中的某个角色。

            //boolean permitted = subject.isPermitted("admin:add"); 判断主体有没有admin:add 权限
            //boolean[] permitted = subject.isPermitted("admin:delete", "book:*"); 判断主体有没有某个权限
            //boolean permittedAll = subject.isPermittedAll("admin:delete", "book:*");   判断主体有没有当前所有权限
        }


    }
}
