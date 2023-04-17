package com.mqttsnet.thinglinks.boot.remote;

import com.mqttsnet.thinglinks.boot.domain.product.entity.Product;
import com.mqttsnet.thinglinks.boot.domain.product.entity.ProductProperties;
import com.mqttsnet.thinglinks.boot.domain.product.entity.ProductServices;
import com.mqttsnet.thinglinks.boot.link.controller.product.ProductController;
import com.mqttsnet.thinglinks.boot.link.controller.product.ProductPropertiesController;
import com.mqttsnet.thinglinks.boot.link.controller.product.ProductServicesController;
import com.mqttsnet.thinglinks.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mqttsnet.thinglinks.common.core.web.domain.AjaxResult.DATA_TAG;

/**
 * @program: thinglinks
 * @description: 产品管理服务
 * @packagename: com.mqttsnet.thinglinks.link.api
 * @author: shisen
 * @date: 2022-07-26
 **/
@Component
public class RemoteProductService {
    @Autowired
    private ProductController controller;
    @Autowired
    private ProductServicesController psController;
    @Autowired
    private ProductPropertiesController ppController;

    /**
     * 通过标识查询产品
     *
     * @param productIdentification
     * @return
     */
    public R<Product> selectByProductIdentification(String productIdentification) {
        return (R<Product>) controller.selectByProductIdentification(productIdentification);
    }

    /**
     * 查询服务信息
     *
     * @param id
     * @return
     */
    public R<ProductServices> selectProductServicesById(Long id) {
        return R.ok((ProductServices) psController.getInfo(id).get(DATA_TAG));

    }


    /**
     * 查询属性信息
     *
     * @return
     */
    public R<ProductProperties> selectByIdProperties(Long id) {
        return R.ok((ProductProperties) ppController.getInfo(id).get(DATA_TAG));
    }


    public R<List<Product>> selectAllProduct(String status) {
        return (R<List<Product>>) controller.selectAllProductByStatus(status);
    }

    public R<?> selectProductByProductIdentificationList(List<String> productIdentificationList) {
        return controller.selectProductByProductIdentificationList(productIdentificationList);
    }

}
