package com.meituan.pay.finsecurity.dao.repository;

import com.meituan.pay.finsecurity.dao.mapper.DataRuleMapper;
import com.meituan.pay.finsecurity.po.DataRule;
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
public class DataRuleRepoTest {
    @InjectMocks
    private DataRuleRepo dataRuleRepo;

    @Mock
    private DataRuleMapper dataRuleMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<DataRule> dataRuleList = new ArrayList<>();
        DataRule dataRule = new DataRule();
        dataRuleList.add(dataRule);
        when(dataRuleMapper.selectByExample(any())).thenReturn(dataRuleList);
    }

    @Test
    public void test001SelectByEventId() {
        List<DataRule> dataRuleList = dataRuleRepo.selectByEventId(1L);
        Assert.assertTrue(!CollectionUtils.isEmpty(dataRuleList));
    }
}