package com.meituan.pay.finsecurity.dao.mapper;

import com.dianping.zebra.group.router.ZebraForceMasterHelper;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.EventRuleExample;
import com.meituan.pay.finsecurity.po.Vector;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-db.xml")
public class EventRuleMapperIntegration {

    @Autowired
    private EventRuleMapper eventRuleMapper;

    @Test
    public void test001InsertSelectiveTest() {
        EventRule eventRule = obtainEventRule();
        eventRule.setVectorList(null);
        int affectLine = eventRuleMapper.insertSelective(eventRule);
        System.out.println(eventRule);

        Assert.assertTrue(affectLine > 0);
    }

    @Test
    public void test002SelectByPrimaryKeyTest() {
        EventRule eventRule = obtainEventRule();
        eventRuleMapper.insertSelective(eventRule);

        ZebraForceMasterHelper.forceMasterInLocalContext();
        EventRule eventRuleDB = eventRuleMapper.selectByPrimaryKey(eventRule.getId());
        ZebraForceMasterHelper.clearLocalContext();
        System.out.println(eventRuleDB);

        Assert.assertTrue(eventRule.equals(eventRuleDB));
    }

    @Test
    public void test003SelectByExampleTest() {
        EventRuleExample example = new EventRuleExample();
        example.createCriteria().andStatusEqualTo(StatusEnum.ON);

        ZebraForceMasterHelper.forceMasterInLocalContext();
        List<EventRule> eventRuleListDB = eventRuleMapper.selectByExample(example);
        Assert.assertTrue(CollectionUtils.isNotEmpty(eventRuleListDB));
    }

    private EventRule obtainEventRule() {
        EventRule eventRule = new EventRule();
        eventRule.setId(System.currentTimeMillis());
        eventRule.setCode(String.valueOf(System.currentTimeMillis()));
        eventRule.setName(String.valueOf(System.currentTimeMillis()));

        List<Vector> vectorList = new ArrayList<>();
        Vector vector = new Vector();
        vector.setName(String.valueOf(System.currentTimeMillis()));
        vector.setExpr(String.valueOf(System.currentTimeMillis()));
        vector.setAlias(String.valueOf(System.currentTimeMillis()));
        vectorList.add(vector);
        eventRule.setVectorList(vectorList);

        eventRule.setExtendedData(String.valueOf(System.currentTimeMillis()));
        eventRule.setStatus(StatusEnum.ON);
        return eventRule;
    }
}