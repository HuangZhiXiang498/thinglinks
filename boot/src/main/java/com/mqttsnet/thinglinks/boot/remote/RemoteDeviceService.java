package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.link.controller.device.DeviceController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 设备管理服务
 *
 * @author shisen
 */
@Component
public class RemoteDeviceService {

    @Autowired
    private DeviceController deviceController;


    /**
     * 查询产品下的设备标识
     *
     * @param productIdentification
     * @return
     */
    public R<?> selectByProductIdentification(String productIdentification){
        return deviceController.selectByProductIdentification(productIdentification);
    };

    /**
     * 根据产品标识获取产品所有关联设备
     *
     * @param productIdentification
     * @return
     */
    @GetMapping("/device/selectAllByProductIdentification/{productIdentification}")
    public R<?> selectAllByProductIdentification(String productIdentification){
        return deviceController.selectAllByProductIdentification(productIdentification);
    };

    @PostMapping("/device/selectDeviceByDeviceIdentificationList")
    public R<?> selectDeviceByDeviceIdentificationList(List<String> deviceIdentificationList){
        return deviceController.selectDeviceByDeviceIdentificationList(deviceIdentificationList);
    };
}
