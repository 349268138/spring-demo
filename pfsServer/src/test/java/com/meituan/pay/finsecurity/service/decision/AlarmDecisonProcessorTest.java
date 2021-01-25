package com.meituan.pay.finsecurity.service.decision;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.Vector;
import com.meituan.pay.finsecurity.po.enums.ProcessResultEnum;
import com.meituan.pay.finsecurity.po.enums.StatusEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/11/9.
 */
public class AlarmDecisonProcessorTest {

    @InjectMocks
    private AlarmDecisonProcessor alarmDecisonProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void decideTest() {
        String dataJson = JacksonUtils.toJson(obtainContextData());
        ProcessResultEnum resultEnum = alarmDecisonProcessor.decide(obtainEventRule(), obtainDecisionRule(), dataJson);
        Assert.assertEquals(ProcessResultEnum.PASS, resultEnum);

        ContextData contextData = obtainContextData();
        contextData.setTradeData(obtainFailTradeData());
        dataJson = JacksonUtils.toJson(contextData);
        resultEnum = alarmDecisonProcessor.decide(obtainEventRule(), obtainDecisionRule(), dataJson);
        Assert.assertEquals(ProcessResultEnum.PASS, resultEnum);

        EventRule eventRule = obtainEventRule();
        eventRule.setVectorList(null);
        resultEnum = alarmDecisonProcessor.decide(eventRule, obtainDecisionRule(), dataJson);
        Assert.assertEquals(ProcessResultEnum.PASS, resultEnum);
    }

    private EventRule obtainEventRule() {
        EventRule eventRule = new EventRule();
        eventRule.setId(1L);
        eventRule.setCode("payplatform complete event");
        eventRule.setName("付款平台完成事件");
        eventRule.setStatus(StatusEnum.ON);
        List<Vector> vectorList = new ArrayList<>();
        Vector vectorPartnerId = new Vector();
        vectorPartnerId.setAlias("partner");
        vectorPartnerId.setName("业务线");
        vectorPartnerId.setExpr("return eventData.partnerid");
        vectorList.add(vectorPartnerId);

        Vector vectorBusinessType = new Vector();
        vectorBusinessType.setAlias("businessType");
        vectorBusinessType.setName("业务类型");
        vectorBusinessType.setExpr("return eventData.business_type");

        Vector vectorNotExsit = new Vector();
        vectorNotExsit.setAlias("not exsit");
        vectorNotExsit.setName("不存在验证");
        vectorNotExsit.setExpr("return eventData.not_exsit");
        vectorList.add(vectorNotExsit);

        vectorList.add(vectorBusinessType);

        eventRule.setVectorList(vectorList);
        return eventRule;
    }

    private DecisionRule obtainDecisionRule() {
        DecisionRule decisionRule = new DecisionRule();
        decisionRule.setId(1L);
        decisionRule.setEventId(1L);
        decisionRule.setAlias("monitor complete");
        decisionRule.setName("付款平台完成监控");
        decisionRule.setExpr("if(eventData.status == 64 && tradeData.paycore.inStatus == \"SUCCESS\") return true else return false");
        decisionRule.setStatus(StatusEnum.ON);
        decisionRule.setType(TypeEnum.MONITOR);
        return decisionRule;
    }

    private ContextData obtainContextData() {
        ContextData contextData = new ContextData();
        contextData.setEventData(obtainEventData());
        contextData.setTradeData(obtainSuccessTradeData());
        return contextData;
    }

    private String obtainEventData() {
        return "{\"id\":2340516932,\"partnerid\":0,\"business_type\":1,\"split_funds_records\":null,\"merchantno\":\"22010191646623441\",\"mohash\":2526105,\"notifyurl\":\"thrift://com.sankuai.pay.merchantproduct.mwalletcore:3426\",\"name\":\"于娓\",\"acctype\":2,\"money\":456577,\"city\":320300,\"bank\":3,\"branchid\":53748,\"branchname\":\"中国邮政储蓄银行股份有限公司徐州市分行营业部\",\"account\":0,\"fkaccount\":19,\"fixchannel\":0,\"channel\":0,\"comment\":\"\",\"reason\":\"\",\"status\":64,\"notifystatus\":0,\"addtime\":1603095393,\"sendtime\":1603095393,\"recvtime\":1603095395,\"succtime\":1603095396,\"failtime\":0,\"notifytime\":0,\"modtime\":1603095395,\"priority\":1603102593,\"extended_data\":\"{\\\"transIdForCheckingAccount\\\":\\\"\\\",\\\"feeBizCode\\\":\\\"2005\\\",\\\"isTransfer\\\":false,\\\"billMerchantOrderId\\\":\\\"22010191646623441\\\",\\\"batchTransferInfoList\\\":\\\"[]\\\",\\\"withdrawReqType\\\":0,\\\"needNotifyClearingCenter\\\":true,\\\"unwithdrawToPayTransferWay\\\":\\\"actTransfer\\\",\\\"needNotifyTradeDataCenter\\\":true,\\\"billTradeType\\\":\\\"ZJ03\\\",\\\"billTradeId\\\":\\\"2340516932\\\",\\\"noAvailableCountMoney\\\":0,\\\"final_pay_company_code\\\":\\\"QDB\\\",\\\"billTradeChannel\\\":\\\"QD02\\\",\\\"hasSend\\\":\\\"1\\\",\\\"phyChannelCode\\\":\\\"netsunionAcs\\\",\\\"phyChannelName\\\":\\\"网联ACS全渠道\\\",\\\"logicalChannelAccountNo\\\":\\\"991100000111\\\",\\\"logicalChannelId\\\":181,\\\"payCoreMtpp\\\":\\\"mtpp_1095129106_11560614217\\\",\\\"billAccountTime\\\":0}\",\"card_id\":236548639,\"trade_no\":\"1602748863780\",\"iph_pay_merchant_no\":12000102584584,\"queuing_type\":0,\"pay_to_card_biz_req_code\":2005,\"pay_prod_type\":1,\"pay_to_card_account_type\":101,\"from_sub_account_id\":0,\"fee_code\":\"\",\"order_time\":1603095393000,\"fee\":0,\"return_fee\":1,\"start_pay_date\":0,\"error_code\":\"FT2O0000\",\"error_msg\":\"付款成功\"}";
    }

    private String obtainSuccessTradeData() {
        String key = "paycore";
        String value = "{\"accountType\":101,\"addTime\":1604281048000,\"bankCode\":\"\",\"branchCode\":\"\",\"callerBizType\":0,\"callerId\":10012,\"callerUniqueId\":\"3022967322\",\"customerId\":1,\"disablePayChannelCodes\":[\"qdbhvbepay\",\"qdbpay\"],\"enablePayChannelCodes\":[],\"extendedData\":{\"appId\":\"wxc72f01f43da0083b\",\"billMerchantOrderId\":\"wx1604281046356\",\"billTradeChannel\":\"QD01\",\"billTradeId\":\"3022967322\",\"billTradeType\":\"ZJ04\",\"cardInfoCorrect\":false,\"cardInfoUnCorrectGrayFlag\":false,\"checkName\":false,\"extendPartnerId\":\"10101010\",\"failChannelRetryGrayMark\":true,\"feeBizCode\":\"10101010\",\"fundsPlatformGrayFlag\":true,\"merchantCapitalNo\":\"1020000218\",\"openId\":\"oFSRuJ2Z87J5vjSKD_npLjhv1KN0\",\"transId\":\"3022967322\",\"transferScene\":0,\"useNewIdGrayFlag\":false},\"feeCode\":\"\",\"feeMoney\":0,\"finalLogicalChannelId\":20007,\"finalPhyChannelCode\":\"wxcompanypay\",\"finalPhyChannelId\":20009,\"finalProvisionId\":0,\"fromAccCode\":\"MTZX\",\"fromAccId\":206,\"fromCardNo\":\"\",\"id\":29854749,\"inStatus\":\"SUCCESS\",\"modTime\":1604281050000,\"notifyCount\":1,\"notifyStatus\":\"SUCCESS\",\"notifyTime\":1604281050000,\"notifyUrl\":\"\",\"outBizNo\":\"wx1604281046356\",\"payProdType\":\"T_0_D\",\"preStatus\":\"COMMIT\",\"recordId\":10023643280,\"resultTime\":1604281050000,\"returnCode\":\"FT3S0000\",\"returnFee\":true,\"returnMsg\":\"付款成功\",\"startPayTime\":1604281047000,\"subAccountId\":0,\"taskExt\":\"\",\"toAccName\":\"\",\"toBankId\":301,\"toBankName\":\"微信\",\"toBranchId\":0,\"toBranchName\":\"\",\"toCardNo\":\"\",\"toCdType\":\"UNKNOW\",\"toCityId\":0,\"toCityName\":\"\",\"toComment\":\"微信支付测试\",\"toMoney\":30,\"toPpType\":\"PUBLIC\",\"toProvinceName\":\"\",\"tradeTime\":1604281046000}";
        Map<String, Object> tradeDataMap = new HashMap<>();
        tradeDataMap.put(key, JacksonUtils.jsonToMap(value));
        return JacksonUtils.toJson(tradeDataMap);
    }

    private String obtainFailTradeData() {
        String key = "paycore";
        String value = "{\"accountType\":101,\"addTime\":1604281048000,\"bankCode\":\"\",\"branchCode\":\"\",\"callerBizType\":0,\"callerId\":10012,\"callerUniqueId\":\"3022967322\",\"customerId\":1,\"disablePayChannelCodes\":[\"qdbhvbepay\",\"qdbpay\"],\"enablePayChannelCodes\":[],\"extendedData\":{\"appId\":\"wxc72f01f43da0083b\",\"billMerchantOrderId\":\"wx1604281046356\",\"billTradeChannel\":\"QD01\",\"billTradeId\":\"3022967322\",\"billTradeType\":\"ZJ04\",\"cardInfoCorrect\":false,\"cardInfoUnCorrectGrayFlag\":false,\"checkName\":false,\"extendPartnerId\":\"10101010\",\"failChannelRetryGrayMark\":true,\"feeBizCode\":\"10101010\",\"fundsPlatformGrayFlag\":true,\"merchantCapitalNo\":\"1020000218\",\"openId\":\"oFSRuJ2Z87J5vjSKD_npLjhv1KN0\",\"transId\":\"3022967322\",\"transferScene\":0,\"useNewIdGrayFlag\":false},\"feeCode\":\"\",\"feeMoney\":0,\"finalLogicalChannelId\":20007,\"finalPhyChannelCode\":\"wxcompanypay\",\"finalPhyChannelId\":20009,\"finalProvisionId\":0,\"fromAccCode\":\"MTZX\",\"fromAccId\":206,\"fromCardNo\":\"\",\"id\":29854749,\"inStatus\":\"FAIL\",\"modTime\":1604281050000,\"notifyCount\":1,\"notifyStatus\":\"SUCCESS\",\"notifyTime\":1604281050000,\"notifyUrl\":\"\",\"outBizNo\":\"wx1604281046356\",\"payProdType\":\"T_0_D\",\"preStatus\":\"COMMIT\",\"recordId\":10023643280,\"resultTime\":1604281050000,\"returnCode\":\"FT3S0000\",\"returnFee\":true,\"returnMsg\":\"付款成功\",\"startPayTime\":1604281047000,\"subAccountId\":0,\"taskExt\":\"\",\"toAccName\":\"\",\"toBankId\":301,\"toBankName\":\"微信\",\"toBranchId\":0,\"toBranchName\":\"\",\"toCardNo\":\"\",\"toCdType\":\"UNKNOW\",\"toCityId\":0,\"toCityName\":\"\",\"toComment\":\"微信支付测试\",\"toMoney\":30,\"toPpType\":\"PUBLIC\",\"toProvinceName\":\"\",\"tradeTime\":1604281046000}";
        Map<String, Object> tradeDataMap = new HashMap<>();
        tradeDataMap.put(key, JacksonUtils.jsonToMap(value));
        return JacksonUtils.toJson(tradeDataMap);
    }
}
