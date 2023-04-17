package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.link.controller.device.DeviceInfoController;
import com.mqttsnet.thinglinks.common.core.constant.ServiceNameConstants;
import com.mqttsnet.thinglinks.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 子设备管理服务
 *
 * @author ShiHuan SUN
 */
@Component
public class RemoteDeviceInfoService {

    @Autowired
    private DeviceInfoController deviceInfoController;

    /**
     * 刷新子设备数据模型
     * @param ids
     * @return
     */
    public AjaxResult refreshDeviceInfoDataModel( Long[] ids){
       return deviceInfoController.refreshDeviceInfoDataModel(ids);
    };

}
