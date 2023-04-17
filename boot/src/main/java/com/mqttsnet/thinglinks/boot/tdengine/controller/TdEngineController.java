package com.mqttsnet.thinglinks.boot.tdengine.controller;

import com.mqttsnet.thinglinks.boot.domain.*;
import com.mqttsnet.thinglinks.boot.domain.visual.SelectVisualDto;
import com.mqttsnet.thinglinks.boot.tdengine.service.TdEngineService;
import com.mqttsnet.thinglinks.common.core.domain.R;
import com.mqttsnet.thinglinks.common.core.enums.DataTypeEnum;
import com.mqttsnet.thinglinks.common.core.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassDescription: TdEngine Controller
 * @ClassName: TdEngineController
 * @Author: thinglinks
 * @Date: 2021-12-27 13:46:44
 * @Version 1.0
 */

@RestController
@RequestMapping("/dataOperation")
public class TdEngineController {

    @Autowired
    private TdEngineService tdEngineService;

    private static final Logger log = LoggerFactory.getLogger(TdEngineController.class);

    /**
     * @param dataBaseName 数据库名称
     * @return R<?>
     * @MethodDescription 创建tdEngine数据库
     * @author thinglinks
     * @Date 2021/12/27 16:26
     */
    @PostMapping("/createDb")
    public R<?> createDataBase(@RequestBody() String dataBaseName) {
        try {
            //调用创建数据库方法
            this.tdEngineService.createDateBase(dataBaseName);
            log.info("successful operation: created database '" + dataBaseName + "' success");
            return R.ok();
        }catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }

    /**
     * @param superTableDto 创建超级表需要的入参的实体类
     * @return R<?>
     * @MethodDescription 创建超级表
     * @author thinglinks
     * @Date 2021/12/27 16:26
     */
    @PostMapping("/createSTb")
    public R<?> createSuperTable(@Validated @RequestBody SuperTableDto superTableDto) {
        //从入参对象获取列字段（超级表结构）对象集合
        List<Fields> schemaFields = superTableDto.getSchemaFields();
        //从入参对象获取标签字段对象集合
        List<Fields> tagsFields = superTableDto.getTagsFields();
        //从入参获取数据库名称
        String dataBaseName = superTableDto.getDataBaseName();
        //从入参获取超级表名称
        String superTableName = superTableDto.getSuperTableName();
        //获取列字段对象集合的第一个对象的字段数据类型
        DataTypeEnum dataType = schemaFields.get(0).getDataType();
        //如果该数据类型不是时间戳，打印和返回报错信息
        if (dataType == null || !"timestamp".equals(dataType.getDataType())) {
            log.error("invalid operation: first column must be timestamp");
            return R.fail("invalid operation: the first column must be timestamp");
        }

        try {
            //将列字段对象集合和标签字段对象集合转码为字段Vo类对象集合
            List<FieldsVo> schemaFieldsVoList = FieldsVo.fieldsTranscoding(schemaFields);
            List<FieldsVo> tagsFieldsVoList = FieldsVo.fieldsTranscoding(tagsFields);
            //创建超级表
            this.tdEngineService.createSuperTable(schemaFieldsVoList, tagsFieldsVoList, dataBaseName, superTableName);
            log.info("successful operation: created superTable '" + superTableName + "' success");
            return R.ok();
        } catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }

    /**
     * 添加列字段
     * @param superTableDto
     * @return
     */
    @PostMapping("/addColumnInStb")
    public R<?> addColumnForSuperTable(@RequestBody SuperTableDto superTableDto) {

        String superTableName = superTableDto.getSuperTableName();
        if (StringUtils.isBlank(superTableName)) {
            return R.fail("invalid operation: superTableName can not be empty");
        }

        Fields fields = superTableDto.getFields();
        if (fields == null) {
            return R.fail("invalid operation: fields can not be empty");
        }

        try {
            FieldsVo fieldsVo = FieldsVo.fieldsTranscoding(fields);
            this.tdEngineService.addColumnForSuperTable(superTableName, fieldsVo);
            log.info("successful operation: add column for superTable '" + superTableName + "' success");
            return R.ok();
        } catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }

    /**
     * 删除列字段
     * @param superTableDto
     * @return
     */
    @PostMapping("/dropColumnInStb")
    public R<?> dropColumnForSuperTable(@RequestBody SuperTableDto superTableDto) {

        String superTableName = superTableDto.getSuperTableName();
        if (StringUtils.isBlank(superTableName)) {
            return R.fail("invalid operation: superTableName can not be empty");
        }

        Fields fields = superTableDto.getFields();
        if (fields == null) {
            return R.fail("invalid operation: fields can not be empty");
        }

        try {
            FieldsVo fieldsVo = FieldsVo.fieldsTranscoding(fields);
            this.tdEngineService.addColumnForSuperTable(superTableName, fieldsVo);
            log.info("successful operation: drop column for superTable '" + superTableName + "' success");
            return R.ok();
        } catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }

    /**
    *@MethodDescription 创建超级表的子表
    *@param tableDto 创建超级表的子表需要的入参的实体类
    *@return R<?>
    *@author thinglinks
    *@Date 2021/12/30 14:15
    */
    @PostMapping("/createTb")
    public R<?> createTable(@Validated @RequestBody TableDto tableDto) {
        try {
            this.tdEngineService.createTable(tableDto);
            log.info("successful operation: create table success");
            return R.ok("successful operation: create table success");
        }  catch (Exception e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
            log.error(message);
            return R.fail(message);
        }
    }

    /**
    *@MethodDescription 插入数据
    *@param tableDto 插入数据需要的入参的实体类
    *@return R<?>
    *@author thinglinks
    *@Date 2022/1/10 14:43
    */
    @PostMapping("/insertData")
    public R<?> insertData(@Validated @RequestBody TableDto tableDto) {
        try {
            List<Fields> tagsFieldValues = tableDto.getTagsFieldValues();
            for (Fields fields : tagsFieldValues) {
                if (StringUtils.isBlank(fields.getFieldName()) || fields.getFieldValue() == null) {
                    log.error("invalid operation: fieldName or fieldValue can not be empty");
                    return R.fail("invalid operation: fieldName or fieldValue can not be empty");
                }
            }
            this.tdEngineService.insertData(tableDto);
            log.info("successful operation: insert data success");
            return R.ok();
        } catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }

    /**
    *@MethodDescription 根据时间戳查询数据
    *@param selectDto 查询数据需要的入参的实体类
    *@return R<?>
    *@author thinglinks
    *@Date 2022/1/10 14:44
    */
    @PostMapping("/getDataByTimestamp")
    public R<?> getDataByTimestamp(@Validated @RequestBody SelectDto selectDto) {
        try {
            return R.ok(this.tdEngineService.selectByTimesTamp(selectDto));
        }catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }

    @PostMapping("/getCountByTimestamp")
    public R<?> getCountByTimestamp(@Validated @RequestBody SelectDto selectDto) {
        try {
            return R.ok(this.tdEngineService.getCountByTimesTamp(selectDto));
        }catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }


    /**
     * @MethodDescription 查询最新数据
     * @param selectDto
     * @return R<?>
     */
    @PostMapping("/getLastData")
    public R<?> getLastData(@Validated @RequestBody SelectDto selectDto) {
        try {
            return R.ok(this.tdEngineService.getLastData(selectDto));
        }catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }


    /**
     * @param selectVisualDto
     * @return R<?>
     * @MethodDescription 可视化数据  返回格式，时间，数值列
     * 历史数据 SELECT voltage ,ts FROM test.meters where ts between '2017-07-14 02:40:00.000' and '2017-07-14 02:40:00.001' LIMIT [topnums]
     * ts > now - 24h;
     * 实时数据 select col from table LIMIT [topnums]
     * 聚合数据 select [avg/max/sum/count..](col) from table where ts between '2017-07-14 02:40:00.000' and '2017-07-14 02:40:00.001' group by col LIMIT [topnums]
     */
    @PostMapping("/getVisualizeData")
    public R<?> getVisualizeData(@Validated @RequestBody SelectVisualDto selectVisualDto) {
        try {
            if (selectVisualDto.getType() == 0) {//查询历史
                return R.ok(this.tdEngineService.getHistoryData(selectVisualDto));
            }else if(selectVisualDto.getType() == 1) {//查询实时
                return R.ok(this.tdEngineService.getRealtimeData(selectVisualDto));
            }else {//查询聚合
                return R.ok(this.tdEngineService.getAggregateData(selectVisualDto));
            }
        } catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {
            }
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }
    /**
     * @MethodDescription 根据超级表查询包含Tags的最新数据集合
     * @param tagsSelectDao
     * @return R<?>
     */
    @PostMapping("/getLastDataByTags")
    public R<Map<String, Map<String, Object>>> getLastDataByTags(@Validated @RequestBody TagsSelectDao tagsSelectDao) {
        try {
            return R.ok(this.tdEngineService.getLastDataByTags(tagsSelectDao));
        }catch (UncategorizedSQLException e) {
            String message = e.getCause().getMessage();
            try {
                message = message.substring(message.lastIndexOf("invalid operation"));
            } catch (Exception ex) {}
            log.error(message);
            return R.fail(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(e.getMessage());
        }
    }
}
