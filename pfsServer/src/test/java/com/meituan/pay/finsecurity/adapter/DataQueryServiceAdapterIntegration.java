package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.service.data.DataQueryProcessorFactory;
import com.meituan.pay.finsecurity.service.data.TradeDataService;
import com.meituan.pay.finsecurity.service.data.RpcDataQueryProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.meituan.pay.finsecurity.constant.TradeDataConstant.*;
import static org.mockito.Mockito.when;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public class DataQueryServiceAdapterIntegration {

    @InjectMocks
    private TradeDataService dataQueryService;

    @Mock
    private DataQueryProcessorFactory dataQueryFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(dataQueryFactory.obtainProcessor(DataAccessTypeEnum.RPC)).thenReturn(new RpcDataQueryProcessor());
    }

    @Test
    public void queryTradeDataTest() {
        String tradeData = dataQueryService.queryTradeData(obtainDataRuleList(), obtainEventData("paycore"));
        Assert.assertNotNull(tradeData);
    }

    private List<DataRule> obtainDataRuleList() {
        List<DataRule> dataRuleList = new ArrayList<>();
        DataRule dataRule = new DataRule();
        dataRule.setId(1L);
        dataRule.setEventId(1L);
        dataRule.setName("付款核心数据配置");
        dataRule.setAlias("paycore");
        dataRule.setAddress("com.sankuai.pay.fundstransfer.paycore:9006:localhost");
        dataRule.setType(DataAccessTypeEnum.RPC);
        dataRule.setKeyExpr("eventData.trade_no");
        dataRuleList.add(dataRule);
        return dataRuleList;
    }

    private String obtainEventData(String code) {
        if(code.equals("paycore")) {
            return PAYCORE_TRADEDATA;
        }else if(code.equals("transfer")) {
            return TRANSFER_TRADEDATA;
        }else if(code.equals("split")){
            return SPLIT_TRADEDATA;
        }else
            return null;
    }
}
