package com.meituan.pay.finsecurity.controller;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.EventRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/12/14 9:32 下午
 */
public class EventRuleControllerTest {

    @InjectMocks
    private EventRuleController eventRuleController;

    @Mock
    private EventRuleRepo eventRuleRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test005EventSearchAllCode() {
        List<EventRule> eventRuleList = new ArrayList<>();
        EventRule eventRule = new EventRule();
        eventRule.setId(1L);
        eventRule.setCode("1");
        eventRuleList.add(eventRule);
        when(eventRuleRepo.selectAll()).thenReturn(eventRuleList);

        Map<String, Object> result = JacksonUtils.jsonToMap(eventRuleController.eventSearchAll());
        Assert.assertTrue("0".equals(String.valueOf(result.get("code"))));

        RuntimeException runtimeException = new RuntimeException("selectAll error");
        when(eventRuleRepo.selectAll()).thenThrow(runtimeException);
        result = JacksonUtils.jsonToMap(eventRuleController.eventSearchAll());
        Assert.assertTrue("1".equals(String.valueOf(result.get("code"))));
    }
}
