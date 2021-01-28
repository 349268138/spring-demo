package com.meituan.pay.finsecurity.controller;

import com.github.pagehelper.Page;
import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.dao.repository.DecisionRuleRepo;
import com.meituan.pay.finsecurity.dao.repository.EventRuleRepo;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.service.data.TradeEventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/12/14 9:32 下午
 */
public class DecisionRuleControllerTest {

    @InjectMocks
    private DecisionRuleController decisionRuleController;

    @Mock
    private DecisionRuleRepo decisionRuleRepo;

    @Mock
    private EventRuleRepo eventRuleRepo;

    @Mock
    private TradeEventService tradeEventService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test001DecisionSearch() {
        Page page = new Page();
        page.setTotal(10);
        when(decisionRuleRepo.selectExampleByPage(any(DecisionRule.class), anyInt(), anyInt())).thenReturn(page);
        Map<String, Object> result = JacksonUtils.jsonToMap(decisionRuleController.decisionSearch(new DecisionRule(), 1, 10));
        Assert.assertTrue("0".equals(String.valueOf(result.get("code"))));

        RuntimeException runtimeException = new RuntimeException("selectExampleByPage error");
        when(decisionRuleRepo.selectExampleByPage(any(DecisionRule.class), anyInt(), anyInt())).thenThrow(runtimeException);
        result = JacksonUtils.jsonToMap(decisionRuleController.decisionSearch(new DecisionRule(), 1, 10));
        Assert.assertTrue("1".equals(String.valueOf(result.get("code"))));
    }

    @Test
    public void test002DecisionAdd() {
        when(decisionRuleRepo.insertBySelective(any(DecisionRule.class))).thenReturn(1);
        Map<String, Object> result = JacksonUtils.jsonToMap(decisionRuleController.decisionAdd(new DecisionRule()));
        Assert.assertTrue("0".equals(String.valueOf(result.get("code"))));

        RuntimeException runtimeException = new RuntimeException("insertBySelective error");
        when(decisionRuleRepo.insertBySelective(any(DecisionRule.class))).thenThrow(runtimeException);
        result = JacksonUtils.jsonToMap(decisionRuleController.decisionAdd(new DecisionRule()));
        Assert.assertTrue("1".equals(String.valueOf(result.get("code"))));
    }

    @Test
    public void test003DecisionUpdate() {
        when(decisionRuleRepo.updateByIdSelective(any(DecisionRule.class))).thenReturn(1);
        Map<String, Object> result = JacksonUtils.jsonToMap(decisionRuleController.decisionUpdate(1L, new DecisionRule()));
        Assert.assertTrue("0".equals(String.valueOf(result.get("code"))));

        RuntimeException runtimeException = new RuntimeException("updateByIdSelective error");
        when(decisionRuleRepo.updateByIdSelective(any(DecisionRule.class))).thenThrow(runtimeException);
        result = JacksonUtils.jsonToMap(decisionRuleController.decisionUpdate(1L, new DecisionRule()));
        Assert.assertTrue("1".equals(String.valueOf(result.get("code"))));
    }

    @Test
    public void test004DecisionDelete() {
        when(decisionRuleRepo.deleteByPrimaryKey(any())).thenReturn(1);
        Map<String, Object> result = JacksonUtils.jsonToMap(decisionRuleController.decisionDelete(1L));
        Assert.assertTrue("0".equals(String.valueOf(result.get("code"))));

        RuntimeException runtimeException = new RuntimeException("updateByIdSelective error");
        when(decisionRuleRepo.deleteByPrimaryKey(any())).thenThrow(runtimeException);
        result = JacksonUtils.jsonToMap(decisionRuleController.decisionDelete(1L));
        Assert.assertTrue("1".equals(String.valueOf(result.get("code"))));
    }
}
