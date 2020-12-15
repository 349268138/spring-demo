package com.meituan.pay.finsecurity.dao.mapper;

import com.dianping.zebra.group.router.ZebraForceMasterHelper;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.DataRuleExample;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import deps.redis.clients.util.CollectionUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-db.xml")
public class DataRuleMapperIntegration {

    @Autowired
    private DataRuleMapper dataRuleMapper;

    @Test
    public void test001InsertSelectiveTest() {
        DataRule dataRule = obtainDataRule();
        int affectLine = dataRuleMapper.insertSelective(dataRule);
        System.out.println(dataRule);

        Assert.assertTrue(affectLine > 0);
    }

    @Test
    public void test002SelectByPrimaryKeyTest() {
        DataRule dataRule = obtainDataRule();
        dataRuleMapper.insertSelective(dataRule);

        ZebraForceMasterHelper.forceMasterInLocalContext();
        DataRule dataRuleDB = dataRuleMapper.selectByPrimaryKey(dataRule.getId());
        ZebraForceMasterHelper.clearLocalContext();
        System.out.println(dataRuleDB);

        Assert.assertTrue(dataRule.equals(dataRuleDB));
    }

    @Test
    public void test003SelectByExampleTest() {
        DataRuleExample example = new DataRuleExample();
        example.createCriteria().andStatusEqualTo(StatusEnum.ON);

        ZebraForceMasterHelper.forceMasterInLocalContext();
        List<DataRule> dataRuleListDB = dataRuleMapper.selectByExample(example);
        Assert.assertTrue(CollectionUtils.isNotEmpty(dataRuleListDB));
    }

    private DataRule obtainDataRule() {
        DataRule dataRule = new DataRule();
        dataRule.setId(System.currentTimeMillis());
        dataRule.setName(String.valueOf(System.currentTimeMillis()));
        dataRule.setEventId(System.currentTimeMillis());
        dataRule.setAlias(String.valueOf(System.currentTimeMillis()));
        dataRule.setAddress(String.valueOf(System.currentTimeMillis()));
        dataRule.setStatus(StatusEnum.ON);
        dataRule.setType(DataAccessTypeEnum.RPC);
        dataRule.setKeyExpr(String.valueOf(System.currentTimeMillis()));
        return dataRule;
    }
}