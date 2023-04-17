package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.broker.Actors.CloseConnectionActor;
import com.mqttsnet.thinglinks.boot.broker.Actors.PublishActor;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: Broker推送设备消息
 * @Author: ShiHuan SUN
 * @E-mail: 13733918655@163.com
 * @Website: http://thinglinks.mqttsnet.com
 * @CreateDate: 2022/1/14$ 16:46$
 * @UpdateUser: ShiHuan SUN
 * @UpdateDate: 2022/1/14$ 16:46$
 * @UpdateRemark: 修改内容
 * @Version: V1.0
 */
@Component
public class RemotePublishActorService {
    @Autowired
    private PublishActor controller;
    @Autowired
    private CloseConnectionActor ccController;
    /**
     * 通知ThingLins MQTT Broker推送消息
     * @param params
     * topic topic String	是
     * qos 服务等级 Integer	是
     * retain 保留消息  Boolean	是
     * message 消息 String	是
     * @return
     */
    public R sendMessage( Map<String, Object> params){
        return controller.sendMessage(params);
    };


    /**
     * 通知ThingLins MQTT Broker断开链接
     * @param clientIdentifiers  客户端ID
     * @return
     */
    public R closeConnection(@RequestBody List<String> clientIdentifiers){
        return ccController.closeConnection(clientIdentifiers);
    };
}
