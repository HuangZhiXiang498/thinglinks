package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.link.controller.product.ProductPropertiesController;
import com.mqttsnet.thinglinks.boot.rule.controller.RuleController;
import com.mqttsnet.thinglinks.boot.rule.controller.RuleDeviceLinkageController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @InterfaceDescription: 规则服务
 * @InterfaceName: RemoteRuleService
 * @Author: thinglinks
 * @Date: 2021-12-31 10:57:16
 * @Version 1.0
 */

@Component
public class RemoteRuleService {
    @Autowired
    private RuleDeviceLinkageController controller;

    public R<?> triggerDeviceLinkage(String ruleIdentification) {
        return controller.triggerDeviceLinkage(ruleIdentification);
    }

    ;

}
