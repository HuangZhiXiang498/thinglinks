package com.mqttsnet.thinglinks.boot.tdengine.mapper;


import com.mqttsnet.thinglinks.boot.domain.FieldsVo;
import com.mqttsnet.thinglinks.boot.domain.SelectDto;
import com.mqttsnet.thinglinks.boot.domain.TableDto;
import com.mqttsnet.thinglinks.boot.domain.TagsSelectDao;
import com.mqttsnet.thinglinks.boot.domain.visual.SelectVisualDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassDescription:
 * @ClassName: TdEngineMapper
 * @Author: thinglinks
 * @Date: 2021-12-27 14:52:34
 * @Version 1.0
 */
@Mapper
public interface TdEngineMapper {

    void createDatabase(@Param("dataBaseName") String dataBaseName);

    void createSuperTable(@Param("schemaFields") List<FieldsVo> schemaFields,
                          @Param("tagsFields") List<FieldsVo> tagsFields,
                          @Param("dataBaseName") String dataBaseName,
                          @Param("superTableName") String superTableName);

    void createTable(TableDto tableDto);

    void insertData(TableDto tableDto);

    List<Map<String, Object>> selectByTimestamp(SelectDto selectDto);

    void addColumnForSuperTable(@Param("superTableName") String superTableName,
                                @Param("fieldsVo") FieldsVo fieldsVo);

    void dropColumnForSuperTable(@Param("superTableName") String superTableName,
                                @Param("fieldsVo") FieldsVo fieldsVo);

    void addTagForSuperTable(@Param("superTableName") String superTableName,
                                @Param("fieldsVo") FieldsVo fieldsVo);

    void dropTagForSuperTable(@Param("superTableName") String superTableName,
                                 @Param("fieldsVo") FieldsVo fieldsVo);

    Map<String, Long> getCountByTimestamp(SelectDto selectDto);

    /**
     * 检查表是否存在
     * @param dataBaseName
     * @param tableName 可以为超级表名或普通表名
     * @return
     */
    Integer checkTableExists(@Param("dataBaseName") String dataBaseName, @Param("tableName")String tableName);

    List<Map<String, Object>> getLastData(SelectDto selectDto);

    List<Map<String, Object>> getHistoryData(SelectVisualDto selectVisualDto);

    List<Map<String, Object>> getRealtimeData(SelectVisualDto selectVisualDto);

    List<Map<String, Object>> getAggregateData(SelectVisualDto selectVisualDto);
    
    List<Map<String, Object>> getLastDataByTags(TagsSelectDao tagsSelectDao);
}
