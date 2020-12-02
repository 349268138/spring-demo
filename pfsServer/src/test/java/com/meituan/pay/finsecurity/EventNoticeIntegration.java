package com.meituan.pay.finsecurity;

import com.meituan.pay.finsecurity.sdk.api.EventService;
import com.meituan.pay.finsecurity.sdk.dto.req.EventNoticeReq;
import com.meituan.pay.finsecurity.sdk.dto.resp.EventNoticeResp;
import groovy.util.logging.Slf4j;
import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hhhb
 * @date 2020/11/10 9:30 下午
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-eventServiceTest.xml")
public class EventNoticeIntegration {

    @Autowired
    private EventService eventService;


    @Test
    public void eventNoticeTest() throws TException {
        EventNoticeResp resp = eventService.eventNotice(obtainReq());
        System.out.println(resp);
    }

    private EventNoticeReq obtainReq() {
        EventNoticeReq req = new EventNoticeReq();
        req.setEventCode("payplatform_complete_event");
        req.setEventData(obtainEventData());
        req.setEventTime(System.currentTimeMillis());
        return req;
    }

    private String obtainEventData() {
//        return "{\"id\":2147486562,\"partnerid\":1,\"business_type\":1,\"split_funds_records\":null,\"merchantno\":\"22010191646623441\",\"mohash\":2526105,\"notifyurl\":\"thrift://com.sankuai.pay.merchantproduct.mwalletcore:3426\",\"name\":\"于娓\",\"acctype\":2,\"money\":456577,\"city\":320300,\"bank\":3,\"branchid\":53748,\"branchname\":\"中国邮政储蓄银行股份有限公司徐州市分行营业部\",\"account\":0,\"fkaccount\":19,\"fixchannel\":0,\"channel\":0,\"comment\":\"\",\"reason\":\"\",\"status\":64,\"notifystatus\":0,\"addtime\":1603095393,\"sendtime\":1603095393,\"recvtime\":1603095395,\"succtime\":1603095396,\"failtime\":0,\"notifytime\":0,\"modtime\":1603095395,\"priority\":1603102593,\"extended_data\":\"{\\\"transIdForCheckingAccount\\\":\\\"\\\",\\\"feeBizCode\\\":\\\"2005\\\",\\\"isTransfer\\\":false,\\\"billMerchantOrderId\\\":\\\"22010191646623441\\\",\\\"batchTransferInfoList\\\":\\\"[]\\\",\\\"withdrawReqType\\\":0,\\\"needNotifyClearingCenter\\\":true,\\\"unwithdrawToPayTransferWay\\\":\\\"actTransfer\\\",\\\"needNotifyTradeDataCenter\\\":true,\\\"billTradeType\\\":\\\"ZJ03\\\",\\\"billTradeId\\\":\\\"2340516932\\\",\\\"noAvailableCountMoney\\\":0,\\\"final_pay_company_code\\\":\\\"QDB\\\",\\\"billTradeChannel\\\":\\\"QD02\\\",\\\"hasSend\\\":\\\"1\\\",\\\"phyChannelCode\\\":\\\"netsunionAcs\\\",\\\"phyChannelName\\\":\\\"网联ACS全渠道\\\",\\\"logicalChannelAccountNo\\\":\\\"991100000111\\\",\\\"logicalChannelId\\\":181,\\\"payCoreMtpp\\\":\\\"mtpp_1095129106_11560614217\\\",\\\"billAccountTime\\\":0}\",\"card_id\":236548639,\"trade_no\":\"29784249\",\"iph_pay_merchant_no\":12000102584584,\"queuing_type\":0,\"pay_to_card_biz_req_code\":2005,\"pay_prod_type\":1,\"pay_to_card_account_type\":101,\"from_sub_account_id\":0,\"fee_code\":\"\",\"order_time\":1603095393000,\"fee\":0,\"return_fee\":1,\"start_pay_date\":0,\"error_code\":\"FT2O0000\",\"error_msg\":\"付款成功\"}";
        return "{\"branchid\":0,\"reason\":\"\",\"city\":0,\"fee\":0,\"channel\":0,\"notifytime\":0,\"pay_to_card_account_type\":201,\"from_sub_account_id\":0,\"order_time\":\"2020-12-01 16:34:37\",\"return_fee\":1,\"bank\":3,\"notifystatus\":0,\"fee_code\":\"\",\"business_type\":1,\"iph_pay_merchant_no\":21001430447751,\"pay_prod_type\":1,\"notifyurl\":\"\",\"start_pay_date\":0,\"id\":2701026249,\"partnerid\":0,\"merchantno\":\"1333690923627884617\",\"branchname\":\"\",\"pay_to_card_biz_req_code\":2004,\"failtime\":0,\"extended_data\":\"{\\\"transIdForCheckingAccount\\\":\\\"\\\",\\\"feeBizCode\\\":\\\"2004\\\",\\\"isTransfer\\\":false,\\\"billMerchantOrderId\\\":\\\"1333690923627884617\\\",\\\"batchTransferInfoList\\\":\\\"[]\\\",\\\"withdrawReqType\\\":0,\\\"needNotifyClearingCenter\\\":true,\\\"unwithdrawToPayTransferWay\\\":\\\"actTransfer\\\",\\\"needNotifyTradeDataCenter\\\":true,\\\"billTradeType\\\":\\\"ZJ03\\\",\\\"billTradeId\\\":\\\"2701026249\\\",\\\"noAvailableCountMoney\\\":0,\\\"final_pay_company_code\\\":\\\"QDB\\\",\\\"billTradeChannel\\\":\\\"QD02\\\",\\\"hasSend\\\":\\\"1\\\",\\\"phyChannelCode\\\":\\\"netsunionAcs\\\",\\\"phyChannelName\\\":\\\"网联ACS全渠道\\\",\\\"logicalChannelAccountNo\\\":\\\"991100000111\\\",\\\"logicalChannelId\\\":181,\\\"payCoreMtpp\\\":\\\"mtpp_1157165082_11622767117\\\",\\\"billAccountTime\\\":0}\",\"fixchannel\":0,\"error_msg\":\"付款成功\",\"queuing_type\":0,\"modtime\":1606811679,\"priority\":1606818877,\"acctype\":2,\"card_id\":326383959,\"split_funds_records\":null,\"recvtime\":1606811679,\"money\":20,\"addtime\":1606811677,\"succtime\":1606811680,\"name\":\"董妍洁\",\"trade_no\":\"1157165082\",\"comment\":\"C端取现\",\"error_code\":\"FT2O0000\",\"sendtime\":1606811677,\"mohash\":883604,\"fkaccount\":19,\"account\":0,\"status\":64}, repeated='null', eventTime='1606811979009'}";
    }
}
