package com.mqttsnet.thinglinks.boot.link.controller.product;

import com.mqttsnet.thinglinks.boot.domain.product.entity.ProductProperties;
import com.mqttsnet.thinglinks.boot.link.service.product.ProductPropertiesService;
import com.mqttsnet.thinglinks.boot.log.annotation.Log;
import com.mqttsnet.thinglinks.boot.log.enums.BusinessType;
import com.mqttsnet.thinglinks.boot.security.annotation.PreAuthorize;
import com.mqttsnet.thinglinks.common.core.domain.R;
import com.mqttsnet.thinglinks.common.core.utils.SecurityUtils;
import com.mqttsnet.thinglinks.common.core.utils.poi.ExcelUtil;
import com.mqttsnet.thinglinks.common.core.web.controller.BaseController;
import com.mqttsnet.thinglinks.common.core.web.domain.AjaxResult;
import com.mqttsnet.thinglinks.common.core.web.page.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
* (productProperties)表控制层
*
* @author thinglinks
*/
@RestController
@RequestMapping("/productProperties")
public class ProductPropertiesController extends BaseController {
/**
* 服务对象
*/
@Resource
private ProductPropertiesService productPropertiesService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public ProductProperties selectOne(Long id) {
return productPropertiesService.selectByPrimaryKey(id);
}

    /**
     * 查询产品模型服务属性列表
     */
    @PreAuthorize(hasPermi = "link:productProperties:list")
    @GetMapping("/list")
    public TableDataInfo list(ProductProperties productProperties) {
        startPage();
        List<ProductProperties> list = productPropertiesService.selectProductPropertiesList(productProperties);
        return getDataTable(list);
    }

    /**
     * 导出产品模型服务属性列表
     */
    @PreAuthorize(hasPermi = "link:productProperties:export")
    @Log(title = "产品模型服务属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductProperties productProperties) throws IOException {
        List<ProductProperties> list = productPropertiesService.selectProductPropertiesList(productProperties);
        ExcelUtil<ProductProperties> util = new ExcelUtil<ProductProperties>(ProductProperties.class);
        util.exportExcel(response, list, "产品模型服务属性数据");
    }

    /**
     * 获取产品模型服务属性详细信息
     */
    @PreAuthorize(hasPermi = "link:productProperties:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(productPropertiesService.selectProductPropertiesById(id));
    }

    /**
     * 新增产品模型服务属性
     */
    @PreAuthorize(hasPermi = "link:productProperties:add")
    @Log(title = "产品模型服务属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductProperties productProperties) {
        productProperties.setCreateBy(SecurityUtils.getUsername());
        return toAjax(productPropertiesService.insertProductProperties(productProperties));
    }

    /**
     * 修改产品模型服务属性
     */
    @PreAuthorize(hasPermi = "link:productProperties:edit")
    @Log(title = "产品模型服务属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductProperties productProperties) {
        productProperties.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(productPropertiesService.updateProductProperties(productProperties));
    }

    /**
     * 删除产品模型服务属性
     */
    @PreAuthorize(hasPermi = "link:productProperties:remove")
    @Log(title = "产品模型服务属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productPropertiesService.deleteProductPropertiesByIds(ids));
    }


    /**
     * 查询产品模型服务属性列表
     */
    @GetMapping("/selectAllPropertiesByServiceId/{serviceId}")
    public R selectAllPropertiesByServiceId(@PathVariable("serviceId") Long serviceId) {
        ProductProperties productProperties = new ProductProperties();
        productProperties.setServiceId(serviceId);
        List<ProductProperties> list = productPropertiesService.selectProductPropertiesList(productProperties);
        return R.ok(list);
    }

    @PostMapping("/selectPropertiesByPropertiesIdList")
    public R<?> selectPropertiesByPropertiesIdList(@RequestBody List<Long> propertiesIdList){
        return R.ok(productPropertiesService.selectPropertiesByPropertiesIdList(propertiesIdList));
    }
}
