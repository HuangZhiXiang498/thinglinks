package com.mqttsnet.thinglinks.boot.remote;


import com.mqttsnet.thinglinks.boot.link.controller.product.ProductServicesController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoteProductServicesService {

    @Autowired
    private ProductServicesController controller;

    public R<?> selectAllByProductIdentificationAndStatus(String productIdentification, String status) {
        return controller.selectAllByProductIdentificationAndStatus(productIdentification, status);
    }

    ;

    public R<?> selectProductServicesByIdList(List<Long> serviceIdList) {
        return controller.selectProductServicesByIdList(serviceIdList);

    }

    ;
}
