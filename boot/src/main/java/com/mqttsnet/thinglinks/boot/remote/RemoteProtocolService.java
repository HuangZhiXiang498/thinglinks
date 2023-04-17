package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.link.controller.protocol.ProtocolController;
import com.mqttsnet.thinglinks.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: thinglinks
 * @description: 协议管理服务
 * @packagename: com.mqttsnet.thinglinks.link.api
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2022-07-11 15:15
 **/
@Component
public class RemoteProtocolService {
    @Autowired
    private ProtocolController controller;
    /**
     * 刷新协议脚本缓存
     * @return
     */
    public AjaxResult protocolScriptCacheRefresh(){
      return   controller.protocolScriptCacheRefresh();
    };
}
