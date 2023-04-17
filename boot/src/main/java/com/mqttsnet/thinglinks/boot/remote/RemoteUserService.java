package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.domain.SysUser;
import com.mqttsnet.thinglinks.boot.domain.model.LoginUser;
import com.mqttsnet.thinglinks.boot.system.controller.SysUserController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户服务
 * 
 * @author thinglinks
 */
@Component
public class RemoteUserService
{

    @Autowired
    private SysUserController controller;
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    public R<LoginUser> getUserInfo( String username, String source){
       return controller.info(username);
    };

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo( SysUser sysUser,  String source){
        return controller.register(sysUser);
    };
}
