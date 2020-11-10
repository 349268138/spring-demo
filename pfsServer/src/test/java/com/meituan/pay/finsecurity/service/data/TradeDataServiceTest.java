package com.meituan.pay.finsecurity.service.data;

import com.dianping.cat.util.StringUtils;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author hhhb
 * @date 2020/11/4 2:40 下午
 */
public class TradeDataServiceTest {

    @InjectMocks
    private TradeDataService tradeDataService;

    @Mock
    private DataQueryProcessorFactory dataQueryProcessorFactory;

    @Mock
    private RpcDataQueryProcessor rpcDataQueryProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(dataQueryProcessorFactory.obtainProcessor(DataAccessTypeEnum.RPC)).thenReturn(rpcDataQueryProcessor);
        when(rpcDataQueryProcessor.queryData(any(DataRule.class), anyString())).thenReturn("{}");
    }

    @Test
    public void queryTradeDataError() {
        List<DataRule> dataRuleList = obtainDataRuleList();
        dataRuleList.get(0).setType(null);
        try {
            tradeDataService.queryTradeData(dataRuleList, obtainEventData());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("queryTradeData error, dataRuleList"));
        }
    }

    @Test
    public void queryTradeDataSucess() {
        String tradeData = tradeDataService.queryTradeData(null, null);
        Assert.assertTrue(StringUtils.isEmpty(tradeData));

        tradeData = tradeDataService.queryTradeData(obtainDataRuleList(), obtainEventData());
        Assert.assertTrue(tradeData.equals("{\"paycore\":{}}"));
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

    private String obtainEventData() {
        return "{\"id\":2340516932,\"partnerid\":0,\"business_type\":1,\"split_funds_records\":null,\"merchantno\":\"22010191646623441\",\"mohash\":2526105,\"notifyurl\":\"thrift://com.sankuai.pay.merchantproduct.mwalletcore:3426\",\"name\":\"于娓\",\"acctype\":2,\"money\":456577,\"city\":320300,\"bank\":3,\"branchid\":53748,\"branchname\":\"中国邮政储蓄银行股份有限公司徐州市分行营业部\",\"account\":0,\"fkaccount\":19,\"fixchannel\":0,\"channel\":0,\"comment\":\"\",\"reason\":\"\",\"status\":64,\"notifystatus\":0,\"addtime\":1603095393,\"sendtime\":1603095393,\"recvtime\":1603095395,\"succtime\":1603095396,\"failtime\":0,\"notifytime\":0,\"modtime\":1603095395,\"priority\":1603102593,\"extended_data\":\"{\\\"transIdForCheckingAccount\\\":\\\"\\\",\\\"feeBizCode\\\":\\\"2005\\\",\\\"isTransfer\\\":false,\\\"billMerchantOrderId\\\":\\\"22010191646623441\\\",\\\"batchTransferInfoList\\\":\\\"[]\\\",\\\"withdrawReqType\\\":0,\\\"needNotifyClearingCenter\\\":true,\\\"unwithdrawToPayTransferWay\\\":\\\"actTransfer\\\",\\\"needNotifyTradeDataCenter\\\":true,\\\"billTradeType\\\":\\\"ZJ03\\\",\\\"billTradeId\\\":\\\"2340516932\\\",\\\"noAvailableCountMoney\\\":0,\\\"final_pay_company_code\\\":\\\"QDB\\\",\\\"billTradeChannel\\\":\\\"QD02\\\",\\\"hasSend\\\":\\\"1\\\",\\\"phyChannelCode\\\":\\\"netsunionAcs\\\",\\\"phyChannelName\\\":\\\"网联ACS全渠道\\\",\\\"logicalChannelAccountNo\\\":\\\"991100000111\\\",\\\"logicalChannelId\\\":181,\\\"payCoreMtpp\\\":\\\"mtpp_1095129106_11560614217\\\",\\\"billAccountTime\\\":0}\",\"card_id\":236548639,\"trade_no\":\"1602748863780\",\"iph_pay_merchant_no\":12000102584584,\"queuing_type\":0,\"pay_to_card_biz_req_code\":2005,\"pay_prod_type\":1,\"pay_to_card_account_type\":101,\"from_sub_account_id\":0,\"fee_code\":\"\",\"order_time\":1603095393000,\"fee\":0,\"return_fee\":1,\"start_pay_date\":0,\"error_code\":\"FT2O0000\",\"error_msg\":\"付款成功\"}";
    }
}
