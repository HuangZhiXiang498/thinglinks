package com.mqttsnet.thinglinks.boot.remote;


import com.mqttsnet.thinglinks.boot.link.controller.product.ProductPropertiesController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
public class RemoteProductPropertiesService {
    @Autowired
    private ProductPropertiesController controller;


   public R<?> selectAllByServiceId(@RequestParam("serviceId") Long serviceId) {
        return controller.selectAllPropertiesByServiceId(serviceId);
    }

    ;

    public R<?> selectPropertiesByPropertiesIdList(@RequestBody List<Long> propertiesIdList) {
        return controller.selectPropertiesByPropertiesIdList(propertiesIdList);
    }

    ;
}
