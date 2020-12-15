package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.dao.mapper.EventRuleMapper;
import com.meituan.pay.finsecurity.po.EventRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author wangjinping
 * @date 11/26/2020 13:56 PM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventRuleRepoTest {
    @InjectMocks
    private EventRuleRepo eventRuleRepo;

    @Mock
    private EventRuleMapper eventRuleMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<EventRule> eventRuleList = new ArrayList<>();
        EventRule eventRule = new EventRule();
        eventRuleList.add(eventRule);
        when(eventRuleMapper.selectByExample(any())).thenReturn(eventRuleList);
    }

    @Test
    public void test001SelectByOnStatus() {
        EventRule eventRule = new EventRule();
        List<EventRule> eventRuleList = eventRuleRepo.selectByOnStatus();
        Assert.assertTrue(!CollectionUtils.isEmpty(eventRuleList));
    }

    @Test
    public void test002SelectAll() {
        EventRule eventRule = new EventRule();
        List<EventRule> eventRuleList = eventRuleRepo.selectAll();
        Assert.assertTrue(!CollectionUtils.isEmpty(eventRuleList));
    }
}