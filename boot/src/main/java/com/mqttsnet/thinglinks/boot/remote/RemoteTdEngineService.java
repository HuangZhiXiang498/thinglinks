package com.mqttsnet.thinglinks.boot.remote;

/**
 * @InterfaceDescription: 时序性数据库TdEngine服务
 * @InterfaceName: RemoteTdEngineService
 * @Author: thinglinks
 * @Date: 2021-12-31 10:57:16
 * @Version 1.0
 */

import com.mqttsnet.thinglinks.boot.domain.SelectDto;
import com.mqttsnet.thinglinks.boot.domain.SuperTableDto;
import com.mqttsnet.thinglinks.boot.domain.TableDto;
import com.mqttsnet.thinglinks.boot.domain.TagsSelectDao;
import com.mqttsnet.thinglinks.boot.tdengine.controller.TdEngineController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RemoteTdEngineService {
    @Autowired
    private TdEngineController controller;

    /**
     * @param superTableDto 创建超级表需要的入参的实体类
     * @return R
     * @MethodDescription 创建超级表
     * @author thinglinks
     * @Date 2021/12/27 11:05
     */
    public R<?> createSuperTable(SuperTableDto superTableDto) {
        return controller.createSuperTable(superTableDto);
    }

    ;

    /**
     * @param tableDto 创建超级表的子表需要的入参的实体类
     * @return R
     * @MethodDescription 创建超级表的子表
     * @author thinglinks
     * @Date 2021/12/27 11:06
     */

    public R<?> createTable(TableDto tableDto) {
        return controller.createTable(tableDto);
    }

    ;

    /**
     * @param tableDto 插入数据需要的入参的实体类
     * @return R
     * @MethodDescription 插入数据
     * @author thinglinks
     * @Date 2022/1/10 14:44
     */

    public R<?> insertData(TableDto tableDto) {
        return controller.insertData(tableDto);
    }

    ;


    /**
     * @param selectDto 查询数据需要的入参的实体类
     * @return R
     * @MethodDescription 根据时间戳查询数据
     * @author thinglinks
     * @Date 2022/1/10 14:44
     */
    public R<?> getDataByTimestamp(SelectDto selectDto) {
        return controller.getDataByTimestamp(selectDto);
    }

    ;

    /**
     * @param selectDto
     * @return
     * @MethodDescription 查询最新数据
     */
    public R<?> getLastData(SelectDto selectDto) {
        return controller.getLastData(selectDto);
    }

    ;

    /**
     * 查询最新的数据带标签
     *
     * @param tagsSelectDao
     * @return
     */
    public R<Map<String, Map<String, Object>>> getLastDataByTags(TagsSelectDao tagsSelectDao) {
        return controller.getLastDataByTags(tagsSelectDao);
    }

    ;


}
