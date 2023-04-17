package com.mqttsnet.thinglinks.boot.link.common.listener;

import com.mqttsnet.thinglinks.boot.link.service.device.DeviceService;
import com.mqttsnet.thinglinks.common.core.constant.Constants;
import com.mqttsnet.thinglinks.common.core.text.UUID;
import com.mqttsnet.thinglinks.common.redis.service.RedisService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Description: Redis Key失效事件监听
 * @Author: ShiHuan SUN
 * @E-mail: 13733918655@163.com
 * @Website: http://thinglinks.mqttsnet.com
 * @CreateDate: 2022/3/1$ 15:28$
 * @UpdateUser: ShiHuan SUN
 * @UpdateDate: 2022/3/1$ 15:28$
 * @UpdateRemark: 修改内容
 * @Version: V1.0
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisService redisService;
    @Autowired
    private DeviceService deviceService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        // 避免多个服务监听情况下重复消费
        boolean resultLock = false;
        String uuid = UUID.getUUID();
        try {
            resultLock = redisService.checkLock(expiredKey, uuid, 1000L);
            if (resultLock) {
                log.info("获取分布式锁成功-key：{}，value：{}", expiredKey, uuid);
                if (expiredKey.contains(Constants.DEVICE_RECORD_KEY)){
                    log.info("设备信息缓存失效{}",expiredKey);
                    deviceService.cacheInvalidation(expiredKey.replace(Constants.DEVICE_RECORD_KEY, ""));
                }
            }else {
                log.info("获取分布式锁失败-key：{}，value：{}", expiredKey, uuid);
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            if (resultLock) {
                //释放锁
                boolean result = redisService.releaseDistributedLock(expiredKey, uuid);
                if (result) {
                    log.info("释放分布式锁成功-key：{}，value：{}", expiredKey, uuid);
                } else {
                    log.info("释放分布式锁失败-key：{}，value：{}", expiredKey, uuid);
                }
            }
        }
    }
}
