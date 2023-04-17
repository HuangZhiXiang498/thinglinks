package com.mqttsnet.thinglinks.boot.link.service.device;

import com.alibaba.fastjson.JSONObject;
import com.mqttsnet.thinglinks.boot.domain.device.entity.DeviceDatas;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: ShiHuan Sun
 * @E-mail: 13733918655@163.com
 * @Website: http://thinglinks.mqttsnet.com
 * @CreateDate: 2021/12/26$ 0:27$
 * @UpdateUser: ShiHuan Sun
 * @UpdateDate: 2021/12/26$ 0:27$
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface DeviceDatasService {


    int deleteByPrimaryKey(Long id);

    int insert(DeviceDatas record);

    int insertOrUpdate(DeviceDatas record);

    int insertOrUpdateSelective(DeviceDatas record);

    int insertSelective(DeviceDatas record);

    DeviceDatas selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceDatas record);

    int updateByPrimaryKey(DeviceDatas record);

    int updateBatch(List<DeviceDatas> list);

    int updateBatchSelective(List<DeviceDatas> list);

    int batchInsert(List<DeviceDatas> list);

    /**
     * thinglinks-mqtt基础数据处理
     *
     * @param thinglinksMessage
     */
    void insertBaseDatas(JSONObject thinglinksMessage) throws Exception;


    /**
     * 处理/topo/add Topic边设备添加子设备
     *
     * @param deviceIdentification 设备标识
     * @param body                  数据
     */
    String processingTopoAddTopic(String deviceIdentification, String body) throws Exception;

    /**
     * 处理/topo/delete Topic边设备删除子设备
     *
     * @param deviceIdentification 设备标识
     * @param body                  数据
     */
    String processingTopoDeleteTopic(String deviceIdentification, String body) throws Exception;

    /**
     * 处理/topo/update Topic边设备更新子设备状态
     *
     * @param deviceIdentification 设备标识
     * @param body                  数据
     */
    String processingTopoUpdateTopic(String deviceIdentification, String body) throws Exception;

    /**
     * 处理datas Topic数据上报
     *
     * @param deviceIdentification 设备标识
     * @param body                  数据
     */
    void processingDatasTopic(String deviceIdentification, String body) throws Exception;

    /**
     * 处理/commandResponse Topic边设备返回给物联网平台的命令响应
     *
     * @param deviceIdentification 设备标识
     * @param body                  数据
     */
    void processingTopoCommandResponseTopic(String deviceIdentification, String body) throws Exception;

}




