package com.lyd.config;


import com.lyd.pojo.User;
import com.lyd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的Realm
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //拿到当前用户
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        info.addStringPermission(currentUser.getPerms());

        log.info("对 " + currentUser.getName() + " 授权成功！");

        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");


        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //连接数据库获取信息
        User user = userService.queryUserByName(userToken.getUsername());

        if (user==null){
            return null;
        }

        if (!userToken.getUsername().equals(user.getName())){
            return null;
        }

        //密码认证，shiro做~
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
