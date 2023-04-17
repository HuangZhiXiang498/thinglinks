package com.mqttsnet.thinglinks.boot.link.service.device.impl;

import com.mqttsnet.thinglinks.boot.domain.SysUser;
import com.mqttsnet.thinglinks.boot.domain.device.entity.DeviceLocation;
import com.mqttsnet.thinglinks.boot.domain.model.LoginUser;
import com.mqttsnet.thinglinks.boot.link.mapper.device.DeviceLocationMapper;
import com.mqttsnet.thinglinks.boot.link.service.device.DeviceLocationService;
import com.mqttsnet.thinglinks.boot.security.service.TokenService;
import com.mqttsnet.thinglinks.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service
public class DeviceLocationServiceImpl implements DeviceLocationService {

    @Resource
    private DeviceLocationMapper deviceLocationMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return deviceLocationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DeviceLocation record) {
        return deviceLocationMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(DeviceLocation record) {
        return deviceLocationMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(DeviceLocation record) {
        LoginUser loginUser = tokenService.getLoginUser();
        SysUser sysUser = loginUser.getSysUser();
        record.setCreateBy(sysUser.getUserName());
        record.setUpdateBy(sysUser.getUserName());
        return deviceLocationMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(DeviceLocation record) {
        return deviceLocationMapper.insertSelective(record);
    }

    @Override
    public DeviceLocation selectByPrimaryKey(Long id) {
        return deviceLocationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DeviceLocation record) {
        return deviceLocationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DeviceLocation record) {
        return deviceLocationMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<DeviceLocation> list) {
        return deviceLocationMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<DeviceLocation> list) {
        return deviceLocationMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<DeviceLocation> list) {
        return deviceLocationMapper.batchInsert(list);
    }


    /**
     * 查询设备位置
     *
     * @param id 设备位置主键
     * @return 设备位置
     */
    @Override
    public DeviceLocation selectDeviceLocationById(Long id)
    {
        return deviceLocationMapper.selectDeviceLocationById(id);
    }

    /**
     * 查询设备位置列表
     *
     * @param deviceLocation 设备位置
     * @return 设备位置
     */
    @Override
    public List<DeviceLocation> selectDeviceLocationList(DeviceLocation deviceLocation)
    {
        return deviceLocationMapper.selectDeviceLocationList(deviceLocation);
    }

    /**
     * 新增设备位置
     *
     * @param deviceLocation 设备位置
     * @return 结果
     */
    @Override
    public int insertDeviceLocation(DeviceLocation deviceLocation)
    {
        deviceLocation.setCreateBy(SecurityUtils.getUsername());
        return deviceLocationMapper.insertDeviceLocation(deviceLocation);
    }

    /**
     * 修改设备位置
     *
     * @param deviceLocation 设备位置
     * @return 结果
     */
    @Override
    public int updateDeviceLocation(DeviceLocation deviceLocation)
    {
        deviceLocation.setUpdateBy(SecurityUtils.getUsername());
        return deviceLocationMapper.updateDeviceLocation(deviceLocation);
    }

    /**
     * 批量删除设备位置
     *
     * @param ids 需要删除的设备位置主键
     * @return 结果
     */
    @Override
    public int deleteDeviceLocationByIds(Long[] ids)
    {
        return deviceLocationMapper.deleteDeviceLocationByIds(ids);
    }

	@Override
	public DeviceLocation findOneByDeviceIdentification(String deviceIdentification){
		 return deviceLocationMapper.findOneByDeviceIdentification(deviceIdentification);
	}



}


