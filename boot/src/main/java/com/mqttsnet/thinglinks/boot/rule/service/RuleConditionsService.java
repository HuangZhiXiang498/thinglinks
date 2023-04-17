package com.mqttsnet.thinglinks.boot.rule.service;



import com.mqttsnet.thinglinks.boot.domain.RuleConditions;
import com.mqttsnet.thinglinks.boot.domain.model.RuleConditionsModel;

import java.util.List;

/**
 * @program: thinglinks
 * @description: ${description}
 * @packagename: com.mqttsnet.thinglinks.rule.service
 * @author: ShiHuan Sun
 * @e-mainl: 13733918655@163.com
 * @date: 2022-07-21 18:47
 **/
public interface RuleConditionsService {


    int deleteByPrimaryKey(Long id);

    int insert(RuleConditions record);

    int insertOrUpdate(RuleConditions record);

    int insertOrUpdateSelective(RuleConditions record);

    int insertSelective(RuleConditions record);

    RuleConditions selectByPrimaryKey(Long id);

    List<RuleConditions> selectByRuleId(Long ruleId);

    int updateByPrimaryKeySelective(RuleConditions record);

    int updateByPrimaryKey(RuleConditions record);

    int updateBatch(List<RuleConditions> list);

    int updateBatchSelective(List<RuleConditions> list);

    List<RuleConditions> batchInsert(List<RuleConditions> list);

    int deleteBatchByIds(Long[] ids);

    List<RuleConditionsModel> ruleConditionsListToRuleConditionsModelList(List<RuleConditions> ruleConditionsList);
}

