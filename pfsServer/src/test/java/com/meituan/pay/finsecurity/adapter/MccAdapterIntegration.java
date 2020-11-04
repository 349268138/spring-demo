package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.constant.MccConstant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hhhb
 * @date 2020/11/4 2:27 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-MccAdapterIntegration.xml")
public class MccAdapterIntegration {

    @Autowired
    private MccAdapter mccAdapter;

    @Test
    public void getStringTest(){
        String value = mccAdapter.getString(MccConstant.KEY_DEV, "hb");
        Assert.assertEquals("test", value);

        value = mccAdapter.getString(MccConstant.EVENTDATAMAP_KEY, "hb");
        Assert.assertEquals("{\"fundsRequest\":{\"dataRuleList\":[{\"address\":\"com.sankuai.pay.fundstransfer.paycore:9006:localhost\",\"alias\":\"paycore\",\"eventId\":1,\"id\":1,\"keyExpr\":\"eventData.trade_no\",\"name\":\"付款核心数据配置\",\"type\":\"RPC\"}],\"decisionRuleList\":[{\"alias\":\"decisionRule_01\",\"eventId\":1,\"expr\":\"true\",\"id\":1,\"name\":\"决策规则_01\",\"type\":\"ALARM\"}],\"eventRule\":{\"code\":\"fundsRequest\",\"id\":1,\"name\":\"eventRule_01\",\"vectorList\":[{\"alias\":\"er_01\",\"expr\":\"true\",\"name\":\"事件规则01\"}]}}}", value);
    }
}
