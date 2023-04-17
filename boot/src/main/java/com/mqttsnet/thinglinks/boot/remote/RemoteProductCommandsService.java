package com.mqttsnet.thinglinks.boot.remote;


import com.mqttsnet.thinglinks.boot.link.controller.product.ProductCommandsController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RemoteProductCommandsService {
    @Autowired
    private ProductCommandsController productCommandsController;

    public R<?> selectProductCommandsByIdList(List<Long> commandIdList) {
        return productCommandsController.selectProductCommandsByIdList(commandIdList);
    }
}
