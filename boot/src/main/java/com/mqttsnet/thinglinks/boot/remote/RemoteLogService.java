package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.domain.SysLogininfor;
import com.mqttsnet.thinglinks.boot.domain.SysOperLog;
import com.mqttsnet.thinglinks.boot.system.controller.SysLogininforController;
import com.mqttsnet.thinglinks.boot.system.controller.SysOperlogController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 日志服务
 * 
 * @author thinglinks
 */
@Component
public class RemoteLogService
{
    @Autowired
    private SysOperlogController sysOperlogController;
    @Autowired
    private SysLogininforController logininforController;

    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @param source 请求来源
     * @return 结果
     */
    public void saveLog(SysOperLog sysOperLog, String source){
        sysOperlogController.add(sysOperLog);
    };

    /**
     * 保存访问记录
     *
     * @param sysLogininfor 访问实体
     * @param source 请求来源
     * @return 结果
     */
    public void saveLogininfor( SysLogininfor sysLogininfor,  String source){
        logininforController.add(sysLogininfor);
    };
}
