package com.mqttsnet.thinglinks.boot.log.service;

import com.mqttsnet.thinglinks.boot.domain.SysOperLog;
import com.mqttsnet.thinglinks.boot.remote.RemoteLogService;
import com.mqttsnet.thinglinks.common.core.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 * 
 * @author thinglinks
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog)
    {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER);
    }
}
