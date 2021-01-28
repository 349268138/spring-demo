package com.meituan.pay.finsecurity.script;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.pay.finsecurity.po.ContextData;
import com.meituan.pay.finsecurity.po.DecisionRule;
import com.meituan.pay.finsecurity.po.EventRule;
import com.meituan.pay.finsecurity.po.Vector;
import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.service.data.DataQueryProcessorFactory;
import com.meituan.pay.finsecurity.service.data.RpcDataQueryProcessor;
import com.meituan.pay.finsecurity.service.data.TradeDataService;
import com.meituan.pay.finsecurity.service.decision.AlarmDecisonProcessor;
import com.meituan.pay.finsecurity.service.event.EventProcessor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/12/24.
 */
public class RuleGenerateIIntegration {

    @InjectMocks
    private TradeDataService tradeDataService;

    @InjectMocks
    private EventProcessor eventProcessor;

    @Mock
    private DataQueryProcessorFactory dataQueryProcessorFactory;

    @Before
    public void setUp() {
  MockitoAnnotations.initMocks(this);
  when(dataQueryProcessorFactory.obtainProcessor(DataAccessTypeEnum.RPC)).thenReturn(new RpcDataQueryProcessor());
    }

    @Test
    public void testPaycore() {
  String eventData = "{\"id\":99999999999,\"partnerid\":0,\"business_type\":fillBusinessType,\"split_funds_records\":null,\"merchantno\":\"22010191646623441\",\"mohash\":2526105,\"notifyurl\":\"thrift://com.sankuai.pay.merchantproduct.mwalletcore:3426\",\"name\":\"张三六\",\"acctype\":2,\"money\":1,\"city\":320300,\"bank\":3,\"branchid\":53748,\"branchname\":\"中国邮政储蓄银行股份有限公司徐州市分行营业部\",\"account\":0,\"fkaccount\":19,\"fixchannel\":0,\"channel\":0,\"comment\":\"\",\"reason\":\"\",\"status\":fillStatus,\"notifystatus\":0,\"addtime\":1603095393,\"sendtime\":1603095393,\"recvtime\":1603095395,\"succtime\":1603095396,\"failtime\":0,\"notifytime\":0,\"modtime\":1603095395,\"priority\":1603102593,\"extended_data\":\"{\"transIdForCheckingAccount\":\"\",\"feeBizCode\":\"2005\",\"isTransfer\":false,\"billMerchantOrderId\":\"22010191646623441\",\"batchTransferInfoList\":\"[]\",\"withdrawReqType\":0,\"needNotifyClearingCenter\":true,\"unwithdrawToPayTransferWay\":\"actTransfer\",\"needNotifyTradeDataCenter\":true,\"billTradeType\":\"ZJ03\",\"billTradeId\":\"2340516932\",\"noAvailableCountMoney\":0,\"final_pay_company_code\":\"QDB\",\"billTradeChannel\":\"QD02\",\"hasSend\":\"1\",\"phyChannelCode\":\"netsunionAcs\",\"phyChannelName\":\"网联ACS全渠道\",\"logicalChannelAccountNo\":\"991100000111\",\"logicalChannelId\":181,\"payCoreMtpp\":\"mtpp_1095129106_11560614217\",\"billAccountTime\":0}\",\"card_id\":236548639,\"trade_no\":\"fillTradeNo\",\"iph_pay_merchant_no\":12000102584584,\"queuing_type\":0,\"pay_to_card_biz_req_code\":2005,\"pay_prod_type\":1,\"pay_to_card_account_type\":101,\"from_sub_account_id\":0,\"fee_code\":\"\",\"order_time\":1603095393000,\"fee\":0,\"return_fee\":1,\"start_pay_date\":0,\"error_code\":\"FT2O0000\",\"error_msg\":\"付款成功\"}";
  eventData = eventData.replace("fillTradeNo", "30201441").replace("fillStatus", "64").replace("fillBusinessType", "1");

  String tradeData = "{\"paycore\":{\"accountInvokeLogList\":[{\"accountType\":101,\"addTime\":1608712081000,\"bizOrderTime\":1608712081000,\"bizType\":4001,\"comment\":\"H5手动提现场景自动化\",\"customerId\":12000047228993,\"feeCode\":\"\",\"feeMoney\":0,\"finalStatus\":true,\"freezeToken\":0,\"id\":88001424,\"invokeStatus\":\"INVOKE_SUCCESS\",\"modTime\":1608712081000,\"payTaskId\":30201441,\"provisionId\":0,\"returnCode\":0,\"subAccountId\":0,\"toMoney\":1},{\"accountType\":101,\"addTime\":1608712083000,\"bizOrderTime\":1608712083000,\"bizType\":4002,\"comment\":\"H5手动提现场景自动化\",\"customerId\":12000047228993,\"feeCode\":\"\",\"feeMoney\":0,\"finalStatus\":true,\"freezeToken\":0,\"id\":88001425,\"invokeStatus\":\"INVOKE_SUCCESS\",\"modTime\":1608712083000,\"payTaskId\":30201441,\"provisionId\":1489653837684,\"returnCode\":0,\"subAccountId\":0,\"toMoney\":1}],\"payRecordList\":[{\"addTime\":1608712083000,\"channelTradeNo\":\"1608712083020\",\"id\":10023918740,\"logicalChannelId\":1006,\"modTime\":1608712083000,\"payTaskAddTime\":1608712081000,\"payTaskId\":30201441,\"phyChannelCode\":\"fundscoreqachannel1\",\"phyChannelId\":36,\"provisionId\":1489653837684,\"receiveData\":\"()\",\"returnCode\":\"FT3S0000\",\"returnMsg\":\"付款成功\",\"sendTime\":1608712083000,\"status\":\"SUCCESS\",\"successTime\":1608712083000,\"toCdType\":\"DEBIT\",\"toPpType\":\"PRIVATE\"}],\"payTask\":{\"accountType\":101,\"addTime\":1608712081000,\"bankCode\":\"105100000017\",\"branchCode\":\"\",\"callerBizType\":0,\"callerId\":10001,\"callerUniqueId\":\"3023449318\",\"customerId\":12000047228993,\"disablePayChannelCodes\":[\"qdbhvbepay\",\"qdbpay\"],\"enablePayChannelCodes\":[],\"extendedData\":{\"billMerchantOrderId\":\"22012231624355994\",\"billTradeChannel\":\"QD02\",\"billTradeId\":\"3023449318\",\"billTradeType\":\"ZJ03\",\"callerExtendPartnerId\":\"10101010\",\"cardInfoCorrect\":true,\"cardInfoUnCorrectGrayFlag\":false,\"checkName\":false,\"failChannelRetryGrayMark\":true,\"feeBizCode\":\"2005\",\"fundsPlatformGrayFlag\":true,\"transId\":\"3023449318\",\"transferScene\":0,\"useNewIdGrayFlag\":false},\"feeCode\":\"\",\"feeMoney\":0,\"finalLogicalChannelId\":1006,\"finalPhyChannelCode\":\"fundscoreqachannel1\",\"finalPhyChannelId\":36,\"finalProvisionId\":1489653837684,\"fromAccCode\":\"QDB\",\"fromAccId\":19,\"fromCardNo\":\"\",\"id\":30201441,\"inStatus\":\"fillInStatus\",\"modTime\":1608712083000,\"notifyCount\":1,\"notifyStatus\":\"SUCCESS\",\"notifyTime\":1608712083000,\"notifyUrl\":\"\",\"outBizNo\":\"22012231624355994\",\"payProdType\":\"T_2_H\",\"preStatus\":\"COMMIT\",\"recordId\":10023918740,\"resultTime\":1608712083000,\"returnCode\":\"FT3S0000\",\"returnFee\":true,\"returnMsg\":\"付款成功\",\"startPayTime\":1608712080000,\"subAccountId\":0,\"taskExt\":\"\",\"toAccName\":\"自动化\",\"toBankId\":7,\"toBankName\":\"中国建设银行\",\"toBranchId\":0,\"toBranchName\":\"\",\"toCardNo\":\"6217000010046811100\",\"toCdType\":\"DEBIT\",\"toCityId\":110000,\"toCityName\":\"北京市\",\"toComment\":\"H5手动提现场景自动化\",\"toMoney\":1,\"toPpType\":\"PRIVATE\",\"toProvinceName\":\"北京市\",\"tradeTime\":1608712080000}}}";

  //paycore.payTask
  tradeData = tradeData.replace("fillInStatus", "FAIL");

  //paycore.payRecordList
  Map<String, Object> jsonToMap = JacksonUtils.jsonToMap(tradeData);
  List<LinkedHashMap> payRecordList = (List<LinkedHashMap>) (((Map<String, Object>) (jsonToMap.get("paycore"))).get("payRecordList"));
  payRecordList.add((LinkedHashMap) JacksonUtils.jsonToMap(JacksonUtils.toJson(payRecordList.get(0))));
  payRecordList.get(0).put("status", "SUCCESS");
  payRecordList.get(1).put("status", "FAIL_RETUEN");
  ((Map<String, Object>) (jsonToMap.get("paycore"))).put("payRecordList", payRecordList);
  tradeData = JacksonUtils.toJson(jsonToMap);

//  tradeData = JacksonUtils.toJson(tradeData);
  String dataJson = obtainDataJson(eventData, tradeData);
  EventRule eventRule = new EventRule();

  //通道重复付款
//  String decisionExpr = "if(eventData.business_type != 1 || eventData.status == 128) return true; if(tradeData.paycore.payTask.inStatus.equals(\"FAIL\")) return true; tradeData.paycore.payRecordList.stream().filter({payRecord -> payRecord.status.equals(\"SUCCESS\") || payRecord.status.equals(\"FAIL_RETUEN\")}).count() < 2";

  //task与recored状态不一致
//  String decisionExpr = "import java.util.stream.Collectors; if(eventData.business_type != 1 || eventData.trade_no.equals(\"\")) return true; def payTask = tradeData.paycore.payTask; def payRecordList = tradeData.paycore.payRecordList; Map<Long, Long> recordStatusCountMap = payRecordList.stream().collect(Collectors.groupingBy({payRecord -> payRecord.status}, Collectors.counting())); if(payTask.inStatus.equals(\"SUCCESS\") && recordStatusCountMap.get(\"SUCCESS\") != 1) return false; if(payTask.inStatus.equals(\"FAIL\") && Objects.nonNull(recordStatusCountMap.get(\"SUCCESS\"))) return false; return true;";

  //付款平台与付款核心状态不一致
//  String decisionExpr = "if(eventData.business_type != 1) return true; if(eventData.status == 128 && Objects.isNull(tradeData.paycore)) return true; if(Objects.isNull(tradeData.paycore)) return false; def payTask = tradeData.paycore.payTask; if(eventData.status == 128 && payTask.inStatus.equals(\"FAIL\")) return true; if(eventData.status == 64 && payTask.inStatus.equals(\"SUCCESS\")) return true; if(eventData.status == 160 && payTask.inStatus.equals(\"FAIL_RETURN\")) return true; return false;";

  //公司主体不一致
  String decisionExpr = "if(eventData.business_type != 1 || eventData.status != 64) return true; if(tradeData.paycore.payTask.fromAccId != tradeData.paycore.logicalChannelInfo.accountId) return false; return true;";

  //分账主单与子单不一致
//  String decisionExpr = "if(eventData.business_type != 4 || eventData.business_type != 6 || eventData.status != 64) return true; if(tradeData.paycore.payTask.fromAccId) return false; if(eventData.fkaccount != tradeData.paycore.logicalChannel.accountId) return false; return true;";


  DecisionRule decisionRule = new DecisionRule();
  decisionRule.setExpr(decisionExpr);

  AlarmDecisonProcessor decisonProcessor = new AlarmDecisonProcessor();
  decisonProcessor.decide(eventRule, decisionRule, dataJson);
    }

    @Test
    public void testSplit() {

  String eventData = "{\"branchid\":0,\"reason\":\"FT2O0000(付款成功)\",\"city\":0,\"fee\":0,\"channel\":0,\"notifytime\":0,\"pay_to_card_account_type\":101,\"from_sub_account_id\":0,\"order_time\":\"1970-01-01 08:00:00\",\"return_fee\":1,\"bank\":306,\"notifystatus\":0,\"fee_code\":\"\",\"business_type\":4,\"iph_pay_merchant_no\":11000009241576,\"pay_prod_type\":0,\"notifyurl\":\"http://peisong-in.sankuai.com/pay/fundsTransfer/platformAccount/notify\",\"start_pay_date\":0,\"id\":3069710823,\"partnerid\":863,\"merchantno\":\"1621605753\",\"branchname\":\"\",\"pay_to_card_biz_req_code\":0,\"failtime\":0,\"extended_data\":\"{\"merchantCapitalNo\":11000009241576,\"feeBizCode\":\"11000103675955\",\"fromIph\":11000103675955,\"transferScene\":0,\"billMerchantOrderId\":\"1621605753\",\"isMafkaMaxPriorityFlag\":false,\"batchTransferInfoList\":\"[]\",\"platformStartPayTime\":1609925699,\"needNotifyClearingCenter\":true,\"extendPartnerId\":11000103675955,\"needNotifyTradeDataCenter\":true,\"splitPayFeeList\":[],\"billTradeType\":\"ZJ02\",\"billTradeId\":\"3069710823\",\"billTradeChannel\":\"QD01\",\"hasSend\":\"1\",\"logicalChannelId\":0,\"billAccountTime\":1609925700000}\",\"fixchannel\":0,\"error_msg\":\"\",\"queuing_type\":1,\"modtime\":1609925700,\"priority\":1609925699,\"acctype\":1,\"card_id\":12000008496389,\"split_funds_records\":\"[{\"bizCode\":0,\"fromAccountNo\":\"1\",\"remark\":\"劳务费\",\"splitAmountCent\":53966,\"toAccountName\":\"安徽杰仕达配送服务外包有限公司\",\"toAccountNo\":\"12000098183072\"},{\"bizCode\":0,\"fromAccountName\":\"安徽杰仕达配送服务外包有限公司\",\"fromAccountNo\":\"12000098183072\",\"remark\":\"劳务费\",\"splitAmountCent\":53966,\"toAccountName\":\"吴童运\",\"toAccountNo\":\"12000008496389\"}]\",\"recvtime\":1609925700,\"money\":53966,\"addtime\":1609925699,\"succtime\":1609925700,\"name\":\"吴童运\",\"trade_no\":\"32101061794661132\",\"comment\":\"劳务费\",\"error_code\":\"\",\"sendtime\":1609925700,\"mohash\":541863,\"fkaccount\":6,\"account\":6,\"status\":64}";
//  eventData = eventData.replace("fillTradeNo", "30201441").replace("fillStatus", "64").replace("fillBusinessType", "1");
  eventData = eventData.replace("fillStatus", "64").replace("fillBusinessType", "1");

  String tradeData = "{\"offLineData\":{\"averageMoney\":\"10950644294\",\"averageCount\":\"166109\",\"maxMoney\":\"10842413\"},\"onLineData\":{\"todayCount\":191809,\"todayMoney\":24738442597},\"split\":{\"transferSplit\":{\"addTime\":1609925701000,\"bizSerialNo\":\"3069710823\",\"channel\":12,\"comments\":\"劳务费\",\"extend\":{\"orderId\":\"1621605753\",\"transId\":\"3069710823\"},\"fee\":0,\"feeData\":\"\",\"feeFundMerchantNo\":0,\"feeStatus\":\"NO_NEED\",\"fundMerchantNo\":11000009241576,\"id\":32101061794661132,\"inputTime\":1609925701000,\"iphPayMerchantNo\":11000009241576,\"modTime\":1609925700000,\"notifyStatus\":\"SUCCESS\",\"notifyTime\":1609925701000,\"payData\":\"\",\"splitData\":\"[{\"a\":53966,\"c\":\"劳务费\",\"t\":12000098183072,\"f\":11000009241576,\"i\":32101061784903737},{\"a\":53966,\"c\":\"劳务费\",\"t\":12000008496389,\"f\":12000098183072,\"i\":32101061784909427}]\",\"status\":\"SUCCESS\",\"succTime\":1609925700000,\"totalAmount\":53966},\"transferSplitRecordList\":[{\"addTime\":1609925700000,\"amount\":53966,\"bizType\":0,\"fromMerchantId\":11000009241576,\"frozenAmount\":53966,\"fundType\":\"SPLIT\",\"id\":32101061784915399,\"layer\":1,\"modTime\":1609925700000,\"platformOrderId\":0,\"splitId\":32101061794661132,\"status\":\"SUCCESS\",\"succTime\":1609925700000,\"toMerchantId\":11000009241576,\"type\":\"FREEZE\",\"withDrawAmount\":0,\"withDrawStatus\":\"NO_NEED\"},{\"addTime\":1609925700000,\"amount\":53966,\"bizType\":0,\"comments\":\"劳务费\",\"fromMerchantId\":11000009241576,\"frozenAmount\":53966,\"fundType\":\"SPLIT\",\"id\":32101061784903737,\"layer\":2,\"modTime\":1609925700000,\"platformOrderId\":0,\"splitId\":32101061794661132,\"status\":\"SUCCESS\",\"succTime\":1609925700000,\"toMerchantId\":12000098183072,\"type\":\"TRANSFER_AND_FREEZE\",\"withDrawAmount\":0,\"withDrawStatus\":\"NO_NEED\"},{\"addTime\":1609925700000,\"amount\":53966,\"bizType\":0,\"comments\":\"劳务费\",\"fromMerchantId\":12000098183072,\"frozenAmount\":0,\"fundType\":\"SPLIT\",\"id\":32101061784909427,\"layer\":3,\"modTime\":1609925700000,\"platformOrderId\":0,\"splitId\":32101061794661132,\"status\":\"SUCCESS\",\"succTime\":1609925700000,\"toMerchantId\":12000008496389,\"type\":\"TRANSFER\",\"withDrawAmount\":53966,\"withDrawStatus\":\"SUCCESS\"}]}}";

  //paycore.payTask
  Map<String, Object> jsonToMap = JacksonUtils.jsonToMap(tradeData);
  LinkedHashMap transferSplit = (LinkedHashMap)(((Map<String, Object>) (jsonToMap.get("split"))).get("transferSplit"));
//  transferSplit.put("status", "SUCCESS");
  transferSplit.put("status", "FAIL");
  ((Map<String, Object>) (jsonToMap.get("split"))).put("transferSplit", transferSplit);
  tradeData = JacksonUtils.toJson(jsonToMap);

  String dataJson = obtainDataJson(eventData, tradeData);
  EventRule eventRule = new EventRule();

  //分账主单与子单不一致
  String decisionExpr = "import java.util.stream.Collectors; if(eventData.status != 64) return true; if(eventData.business_type != 4 || eventData.business_type != 6) return true; def split = tradeData.split.transferSplit; def recordList = tradeData.split.transferSplitRecordList; Map<Long, Long> recordStatusCountMap = recordList.stream().collect(Collectors.groupingBy({record -> record.status}, Collectors.counting())); if(!split.status.equals(\"SUCCESS\") || recordStatusCountMap.get(\"SUCCESS\") != recordList.size()) {System.out.println(split.status + \"+\" + recordStatusCountMap); return false}; return true;";

  DecisionRule decisionRule = new DecisionRule();
  decisionRule.setExpr(decisionExpr);

  AlarmDecisonProcessor decisonProcessor = new AlarmDecisonProcessor();
  decisonProcessor.decide(eventRule, decisionRule, dataJson);

    }

    @Test
    public void testTransfer() {

  String eventData = "{\"branchid\":0,\"reason\":\"\",\"city\":0,\"fee\":0,\"channel\":0,\"notifytime\":0,\"pay_to_card_account_type\":201,\"from_sub_account_id\":0,\"order_time\":\"2021-01-07 00:24:48\",\"return_fee\":1,\"bank\":73,\"notifystatus\":0,\"fee_code\":\"\",\"business_type\":1,\"iph_pay_merchant_no\":21001128673213,\"pay_prod_type\":1,\"notifyurl\":\"\",\"start_pay_date\":0,\"id\":3072340465,\"partnerid\":0,\"merchantno\":\"1346855228330311753\",\"branchname\":\"\",\"pay_to_card_biz_req_code\":2004,\"failtime\":0,\"extended_data\":\"{\"transIdForCheckingAccount\":\"\",\"feeBizCode\":\"2004\",\"isTransfer\":true,\"billMerchantOrderId\":\"1346855228330311753\",\"isMafkaMaxPriorityFlag\":false,\"batchTransferInfoList\":\"[]\",\"platformStartPayTime\":1609950288,\"withdrawReqType\":0,\"needNotifyClearingCenter\":true,\"unwithdrawToPayTransferResult\":\"SUCCESS\",\"unwithdrawToPayTransferWay\":\"actTransfer\",\"needNotifyTradeDataCenter\":true,\"billTradeType\":\"ZJ03\",\"billTradeId\":\"3072340465\",\"noAvailableCountMoney\":400,\"grayToMafka\":false,\"final_pay_company_code\":\"QDB\",\"billTradeChannel\":\"QD02\",\"hasSend\":\"1\",\"phyChannelCode\":\"netsunionAcs\",\"phyChannelName\":\"网联ACS全渠道\",\"logicalChannelAccountNo\":\"991100000111\",\"logicalChannelId\":181,\"payCoreMtpp\":\"mtpp_1215600728_11681279666\",\"billAccountTime\":0}\",\"fixchannel\":0,\"error_msg\":\"付款成功\",\"queuing_type\":0,\"modtime\":1609950291,\"priority\":1609957488,\"acctype\":2,\"card_id\":506284105,\"split_funds_records\":null,\"recvtime\":1609950291,\"money\":400,\"addtime\":1609950288,\"succtime\":1609950291,\"name\":\"丁庆祥\",\"trade_no\":\"1215600728\",\"comment\":\"C端取现\",\"error_code\":\"FT2O0000\",\"sendtime\":1609950288,\"mohash\":5407404,\"fkaccount\":19,\"account\":0,\"status\":64}";
//  eventData = eventData.replace("fillTradeNo", "30201441").replace("fillStatus", "64").replace("fillBusinessType", "1");
  eventData = eventData.replace("fillStatus", "64").replace("fillBusinessType", "1");

  String tradeData = "{\"paycore\":{\"accountInvokeLogList\":[{\"accountType\":201,\"addTime\":1609950289000,\"bizOrderTime\":1609950289000,\"bizType\":4001,\"comment\":\"C端取现\",\"customerId\":21001128673213,\"feeCode\":\"\",\"feeMoney\":0,\"finalStatus\":true,\"freezeToken\":0,\"id\":2424413336,\"invokeStatus\":\"INVOKE_SUCCESS\",\"modTime\":1609950289000,\"payTaskId\":1215600728,\"provisionId\":0,\"returnCode\":0,\"subAccountId\":0,\"toMoney\":400},{\"accountType\":201,\"addTime\":1609950291000,\"bizOrderTime\":1609950291000,\"bizType\":4002,\"comment\":\"C端取现\",\"customerId\":21001128673213,\"feeCode\":\"\",\"feeMoney\":0,\"finalStatus\":true,\"freezeToken\":0,\"id\":2424423294,\"invokeStatus\":\"INVOKE_SUCCESS\",\"modTime\":1609950291000,\"payTaskId\":1215600728,\"provisionId\":31000000023007,\"returnCode\":0,\"subAccountId\":0,\"toMoney\":400}],\"logicalChannelInfo\":{\"accountCode\":\"QDB\",\"accountId\":19,\"accountName\":\"北京钱袋宝支付技术有限公司\",\"accountNo\":\"991100000111\",\"autoDetectRecovery\":true,\"availability\":10000,\"bankBlackList\":[],\"bankBlackListIterator\":[],\"bankBlackListSize\":0,\"channelGroup\":10,\"concurrentNum\":280,\"conf\":\"{\"accountNo\":\"991100000111\"}\",\"detectInterval\":60,\"detectionStage\":0,\"enablePartners\":[999],\"enablePartnersIterator\":[999],\"enablePartnersSize\":1,\"isTimerMaintain\":false,\"logicalChannelId\":181,\"logicalChannelName\":\"网联付款全渠道\",\"maintainTime\":\"2020-12-28 08:05:49\",\"physicalChannelCode\":\"netsunionAcs\",\"physicalChannelId\":52,\"physicalChannelName\":\"网联ACS全渠道\",\"priority\":99,\"provisionId\":31000000023007,\"setAccountCode\":true,\"setAccountId\":true,\"setAccountName\":true,\"setAccountNo\":true,\"setAutoDetectRecovery\":true,\"setAvailability\":true,\"setBankBlackList\":true,\"setChannelGroup\":true,\"setConcurrentNum\":true,\"setConf\":true,\"setDetectInterval\":true,\"setDetectionStage\":true,\"setEnablePartners\":true,\"setIsTimerMaintain\":true,\"setLogicalChannelId\":true,\"setLogicalChannelName\":true,\"setMaintainTime\":true,\"setPhysicalChannelCode\":true,\"setPhysicalChannelId\":true,\"setPhysicalChannelName\":true,\"setPriority\":true,\"setProvisionId\":true,\"setStatus\":true,\"setStopMoney\":true,\"setWarnMoney\":true,\"status\":\"ON\",\"stopMoney\":10000000000,\"warnMoney\":30000000000},\"payRecordList\":[{\"addTime\":1609950291000,\"channelTradeNo\":\"2021010770000116812796660102407\",\"id\":11681279666,\"logicalChannelId\":181,\"modTime\":1609950291000,\"payTaskAddTime\":1609950289000,\"payTaskId\":1215600728,\"phyChannelCode\":\"netsunionAcs\",\"phyChannelId\":52,\"provisionId\":31000000023007,\"receiveData\":\"00000000_00_00000000(00000000(成功))\",\"returnCode\":\"FT3S0000\",\"returnMsg\":\"付款成功\",\"sendTime\":1609950291000,\"status\":\"SUCCESS\",\"successTime\":1609950291000,\"toCdType\":\"DEBIT\",\"toPpType\":\"PRIVATE\"}],\"payTask\":{\"accountType\":201,\"addTime\":1609950289000,\"bankCode\":\"310290000013\",\"branchCode\":\"\",\"callerBizType\":0,\"callerId\":10001,\"callerUniqueId\":\"3072340465\",\"customerId\":21001128673213,\"disablePayChannelCodes\":[\"qdbhvbepay\",\"qdbpay\"],\"enablePayChannelCodes\":[],\"extendedData\":{\"billMerchantOrderId\":\"1346855228330311753\",\"billTradeChannel\":\"QD02\",\"billTradeId\":\"3072340465\",\"billTradeType\":\"ZJ03\",\"cardInfoCorrect\":true,\"cardInfoUnCorrectGrayFlag\":false,\"checkName\":false,\"failChannelRetryGrayMark\":true,\"feeBizCode\":\"2004\",\"fundsPlatformGrayFlag\":true,\"transId\":\"3072340465\",\"transferScene\":0,\"useNewIdGrayFlag\":false},\"feeCode\":\"\",\"feeMoney\":0,\"finalLogicalChannelId\":181,\"finalPhyChannelCode\":\"netsunionAcs\",\"finalPhyChannelId\":52,\"finalProvisionId\":31000000023007,\"fromAccCode\":\"QDB\",\"fromAccId\":19,\"fromCardNo\":\"\",\"id\":1215600728,\"inStatus\":\"SUCCESS\",\"modTime\":1609950291000,\"notifyCount\":1,\"notifyStatus\":\"SUCCESS\",\"notifyTime\":1609950291000,\"notifyUrl\":\"\",\"outBizNo\":\"1346855228330311753\",\"payProdType\":\"T_2_H\",\"preStatus\":\"COMMIT\",\"recordId\":11681279666,\"resultTime\":1609950291000,\"returnCode\":\"FT3S0000\",\"returnFee\":true,\"returnMsg\":\"付款成功\",\"startPayTime\":1609950288000,\"subAccountId\":0,\"taskExt\":\"\",\"toAccName\":\"丁庆祥\",\"toBankId\":73,\"toBankName\":\"浦发银行\",\"toBranchId\":0,\"toBranchName\":\"\",\"toCardNo\":\"6217933975060789\",\"toCdType\":\"DEBIT\",\"toCityId\":0,\"toCityName\":\"\",\"toComment\":\"C端取现\",\"toMoney\":400,\"toPpType\":\"PRIVATE\",\"toProvinceName\":\"\",\"tradeTime\":1609950288000}},\"offLineData\":{\"averageMoney\":\"666814716\",\"averageCount\":\"76628\",\"maxMoney\":\"5000000\"},\"onLineData\":{\"todayCount\":906,\"todayMoney\":7501597}}";

  //paycore.payTask
  Map<String, Object> jsonToMap = JacksonUtils.jsonToMap(tradeData);
  LinkedHashMap transferSplit = (LinkedHashMap)(((Map<String, Object>) (jsonToMap.get("split"))).get("transferSplit"));
//  transferSplit.put("status", "SUCCESS");
  transferSplit.put("status", "FAIL");
  ((Map<String, Object>) (jsonToMap.get("split"))).put("transferSplit", transferSplit);
  tradeData = JacksonUtils.toJson(jsonToMap);

  //paycore.payRecordList
//  Map<String, Object> jsonToMap = JacksonUtils.jsonToMap(tradeData);
//  List<LinkedHashMap> payRecordList = (List<LinkedHashMap>) (((Map<String, Object>) (jsonToMap.get("paycore"))).get("payRecordList"));
//  payRecordList.add((LinkedHashMap) JacksonUtils.jsonToMap(JacksonUtils.toJson(payRecordList.get(0))));
//  payRecordList.get(0).put("status", "SUCCESS");
//  payRecordList.get(1).put("status", "FAIL_RETUEN");
//  ((Map<String, Object>) (jsonToMap.get("paycore"))).put("payRecordList", payRecordList);
//  tradeData = JacksonUtils.toJson(jsonToMap);

//  tradeData = JacksonUtils.toJson(tradeData);
  String dataJson = obtainDataJson(eventData, tradeData);
  EventRule eventRule = new EventRule();

  //分账主单与子单不一致
  String decisionExpr = "import java.util.stream.Collectors; if(eventData.status != 64) return true; if(eventData.business_type != 4 || eventData.business_type != 6) return true; def split = tradeData.split.transferSplit; def recordList = tradeData.split.transferSplitRecordList; Map<Long, Long> recordStatusCountMap = recordList.stream().collect(Collectors.groupingBy({record -> record.status}, Collectors.counting())); if(!split.status.equals(\"SUCCESS\") || recordStatusCountMap.get(\"SUCCESS\") != recordList.size()) {System.out.println(split.status + \"+\" + recordStatusCountMap); return false}; return true;";

  DecisionRule decisionRule = new DecisionRule();
  decisionRule.setExpr(decisionExpr);

  AlarmDecisonProcessor decisonProcessor = new AlarmDecisonProcessor();
  decisonProcessor.decide(eventRule, decisionRule, dataJson);

    }

  @Test
  public void testTemp() throws InterruptedException {

      String eventData = "{\"partnerid\":1,\"business_type\":2}";

      String tradeData = "{}";


      //分账主单与子单不一致
      String decisionExpr = "return false;";

      DecisionRule decisionRule = new DecisionRule();
      decisionRule.setAlias("alarmTest");
      decisionRule.setExpr(decisionExpr);

      String dataJson = obtainDataJson(eventData, tradeData);
      EventRule eventRule = new EventRule();

      List<Vector> vectorList = new ArrayList<>();
      Vector vector1 = new Vector();
      vector1.setAlias("partner");
      vector1.setExpr("return eventData.partnerid");
      vectorList.add(vector1);

      Vector vector2 = new Vector();
      vector2.setAlias("businessType");
      vector2.setExpr("return eventData.business_type");
      vectorList.add(vector2);

      eventRule.setVectorList(vectorList);

      AlarmDecisonProcessor decisonProcessor = new AlarmDecisonProcessor();
      decisonProcessor.decide(eventRule, decisionRule, dataJson);

      Thread.sleep(10000);

  }

    private String obtainDataJson(String eventData, String tradeData) {
      ContextData contextData = new ContextData();
      contextData.setEventData(eventData);
      contextData.setTradeData(tradeData);
      return JacksonUtils.toJson(contextData);
    }
}



