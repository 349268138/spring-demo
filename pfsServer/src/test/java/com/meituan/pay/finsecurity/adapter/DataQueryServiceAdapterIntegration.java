package com.meituan.pay.finsecurity.adapter;

import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.service.data.DataQueryProcessorFactory;
import com.meituan.pay.finsecurity.service.data.RpcDataQueryProcessor;
import com.meituan.pay.finsecurity.service.data.TradeDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.meituan.pay.finsecurity.constant.EventDataConstant.*;
import static com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum.SQUIRREL;
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
        String tradeData = dataQueryService.queryTradeData(obtainTransferDataRuleList(), obtainEventData("transfer"));
        Assert.assertNotNull(tradeData);
    }

//    @Test
//    public void queryTradeDataTest2() {
//        String tradeData = dataQueryFactory.obtainProcessor(SQUIRREL).queryData(obtainDataRuleList().get(0), "2631008701");
//        Assert.assertNotNull(tradeData);
//    }

    private List<DataRule> obtainDataRuleList() {
        List<DataRule> dataRuleList = new ArrayList<>();
        DataRule dataRule = new DataRule();
        dataRule.setId(1L);
        dataRule.setEventId(1L);
        dataRule.setName("squirrel数据配置");
        dataRule.setAlias("squirrel");
        dataRule.setAddress("Hash:fin_security");
        dataRule.setType(SQUIRREL);
        dataRule.setKeyExpr("eventData.partnerid + “-” + eventData.pay_to_card_biz_req_code");
        dataRuleList.add(dataRule);
        return dataRuleList;
    }

    private List<DataRule> obtainTransferDataRuleList() {
        List<DataRule> dataRuleList = new ArrayList<>();
        DataRule dataRule = new DataRule();
        dataRule.setId(4L);
        dataRule.setEventId(1L);
        dataRule.setName("转账数据配置");
        dataRule.setAlias("acttransfer");
        dataRule.setAddress("com.sankuai.pay.fundstransfer.acttransfer:9006:localhost");
        dataRule.setType(DataAccessTypeEnum.RPC);
        dataRule.setKeyExpr("eventData.trade_no");
        dataRuleList.add(dataRule);
        return dataRuleList;
    }

    private String obtainEventData(String code) {
        if(code.equals("paycore")) {
            return PAYCORE_EVENTDATA;
        }else if(code.equals("transfer")) {
            return TRANSFER_EVENTDATA;
        }else if(code.equals("split")){
            return SPLIT_EVENTDATA;
        }else
            return null;
    }
}
