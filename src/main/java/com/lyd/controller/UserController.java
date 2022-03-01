package com.lyd.controller;

import com.lyd.pojo.User;
import com.lyd.service.UserService;
import com.lyd.utills.ResultCommon;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@Api(tags = "用户数据接口")
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口",notes = "传入用户名和密码,返回Code和描述")
    @ApiResponses({
            @ApiResponse(code = 701,message = "登陆成功"),
            @ApiResponse(code = 702,message = "用户名错误"),
            @ApiResponse(code = 703,message = "密码错误")
    })
    @CrossOrigin
    public Map<String, String> login(@RequestBody User user, HttpServletResponse response) throws IOException {
        String username = user.getName();
        String password = user.getPwd();
        log.info(username + ":" + password);
//        Map<Integer, String> map = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        System.out.println(username);
        System.out.println(password);

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            map.put("message",ResultCommon.LOGIN_SUCCESS.getStateDec());
//            response.setStatus(ResultCommon.LOGIN_SUCCESS.getStateCode());
//            response.getWriter().append(ResultCommon.LOGIN_SUCCESS.getStateDec());
            log.info(username + "登录成功,密码为:" + password);
        } catch (UnknownAccountException e) {
            map.put("message", ResultCommon.UNKNOW_ACCOUNT.getStateDec());
//            response.setStatus(ResultCommon.UNKNOW_ACCOUNT.getStateCode());
//            response.getWriter().append(ResultCommon.UNKNOW_ACCOUNT.getStateDec());
            log.error(username + "用户名错误");
        }catch (IncorrectCredentialsException e){
            map.put("message", ResultCommon.INCORRECT_CREDENTIALS.getStateDec());
//            response.setStatus(ResultCommon.INCORRECT_CREDENTIALS.getStateCode());
//            response.getWriter().append(ResultCommon.INCORRECT_CREDENTIALS.getStateDec());
            log.error(username + "密码错误,输入值为" + password);
        }
        return map;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "注册接口")
    @ApiResponses({
            @ApiResponse(code = 601,message = "成功注册为管理员"),
            @ApiResponse(code = 602,message = "成功注册为普通用户"),
            @ApiResponse(code = 603,message = "用户名冲突"),
            @ApiResponse(code = 604,message = "注册失败")
    })
    @CrossOrigin
    public Map<String, String> register(@RequestBody User user1, HttpServletResponse response) {
        User user = new User();
        String username = user1.getName();
        String password = user1.getPwd();
        String nname = user1.getNname();
        Map<String, String> map = new HashMap<>();
        List<User> users = userService.queryAllUser();
        for (User u: users) {
            if(username.equals(u.getName())){
                map.put("message",ResultCommon.REG_SAMEUSERNAME.getStateDec());
//                response.setStatus(ResultCommon.REG_SAMEUSERNAME.getStateCode());
                log.error("注册失败:用户名" + username + "冲突/重复");
                return map;
            }
        }
        user.setName(username);
        user.setPwd(password);
        user.setNname(nname);
        int i = userService.addUser(user);
        if (i > 0) {
            map.put("message",ResultCommon.REG_USER.getStateDec());
//            response.setStatus(ResultCommon.REG_USER.getStateCode());
            log.info(username + "成功注册为用户,密码为:" + password);
        } else {
            map.put("message",ResultCommon.ADD_FAILURE.getStateDec());
//            response.setStatus(ResultCommon.ADD_FAILURE.getStateCode());
            log.error(username + "注册失败");
        }
        return map;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ApiOperation(value = "登出接口")
    @ApiResponse(code = 302,message = "已登出")
    @CrossOrigin
    public void logout(HttpServletResponse response) {
        Subject lvSubject=SecurityUtils.getSubject();
        lvSubject.logout();
        response.setStatus(302);
        log.info("已登出");
    }

    @RequestMapping(value = "/noauth",method = RequestMethod.GET)
    @ApiOperation(value = "无权限自动跳转到此接口")
    @CrossOrigin
    public String unauthorrized(){
        log.info("返回未授权页面");
        return "未经授权无法访问此页面";
    }

}
