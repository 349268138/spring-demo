package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.adapter.SquirrelAdapter;
import com.meituan.pay.finsecurity.constant.SquirrelConstant;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.sdk.api.DataQueryService;
import com.meituan.service.mobile.mtthrift.proxy.ThriftClientProxy;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/11/4 2:39 下午
 */
public class SquirrelDataQueryProcessorTest {

    @InjectMocks
    private SquirrelDataQueryProcessor squirrelDataQueryProcessor;

    @Mock
    private SquirrelAdapter squirrelAdapter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryDataError() {
        try {
            squirrelDataQueryProcessor.queryData(new DataRule(), "");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("squirrel query data error. category"));
        }

        try {
            DataRule dataRule = obtainDataRule();
            dataRule.setAlias("todayMoney");
            dataRule.setName("今日付款金额");
            dataRule.setAddress(SquirrelConstant.TYPE_STRING + "111:" + SquirrelConstant.FIN_SECURITY_CATEGORY);
            squirrelDataQueryProcessor.queryData(dataRule, "");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getCause().getMessage().contains("squirrel method not exsit. address"));
        }
    }

    @Test
    public void queryDataByMGetTest() {
        when(squirrelAdapter.mGet(anyString(), anyList())).thenReturn(obtainMGetData());

        DataRule dataRule = obtainDataRule();
        dataRule.setAlias("todayMoney");
        dataRule.setName("今日付款金额");
        dataRule.setAddress(SquirrelConstant.TYPE_STRING + ":" + SquirrelConstant.FIN_SECURITY_CATEGORY);
        String value = squirrelDataQueryProcessor.queryData(dataRule, "2631008697");
        Assert.assertEquals(obtainMGetData(), value);
    }

    @Test
    public void queryDataByHGetAllTest() {
        when(squirrelAdapter.hGetAll(anyString(), anyString())).thenReturn(obtainHGetAllData());

        DataRule dataRule = obtainDataRule();
        dataRule.setAlias("statData");
        dataRule.setName("统计数据");
        dataRule.setAddress(SquirrelConstant.TYPE_HASH + ":" + SquirrelConstant.FIN_SECURITY_CATEGORY);
        String value = squirrelDataQueryProcessor.queryData(dataRule, "1-0");
        Assert.assertEquals(obtainHGetAllData(), value);
    }

    private String obtainMGetData() {
        return "{\"2631008697\":9000}";
    }

    private String obtainHGetAllData() {
        return "{\"averageCount\":1309876,\"averageMoney\":67826966,\"maxMoney\":9200}";
    }

    private DataRule obtainDataRule() {
        DataRule dataRule = new DataRule();
        dataRule.setType(DataAccessTypeEnum.SQUIRREL);
        dataRule.setEventId(1L);
        dataRule.setId(1L);
        dataRule.setStatus(StatusEnum.ON);
        return dataRule;
    }
}
