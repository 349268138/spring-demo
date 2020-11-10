package com.meituan.pay.finsecurity.script;

import com.meituan.pay.finsecurity.constant.ScriptConstant;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/11/4.
 */
public class GroovyScriptTest {

    @Test
    public void scriptSuccessTest() {
        String expr = "return eventData.id";
        Long expectedId = 2340516932L;
        Long actualId = Long.parseLong(GroovyScript.script(ScriptConstant.EVENT_DATA, obtainJsonData(), expr).toString());
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void scriptErrorTest() {
        String expr = "return eventData1.id";
        try {
            GroovyScript.script(ScriptConstant.EVENT_DATA, obtainJsonData(), expr);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("excute script error"));
        }
    }

    private String obtainJsonData() {
        return "{\"id\":2340516932,\"partnerid\":0,\"business_type\":1,\"split_funds_records\":null,\"merchantno\":\"22010191646623441\",\"mohash\":2526105,\"notifyurl\":\"thrift://com.sankuai.pay.merchantproduct.mwalletcore:3426\",\"name\":\"于娓\",\"acctype\":2,\"money\":456577,\"city\":320300,\"bank\":3,\"branchid\":53748,\"branchname\":\"中国邮政储蓄银行股份有限公司徐州市分行营业部\",\"account\":0,\"fkaccount\":19,\"fixchannel\":0,\"channel\":0,\"comment\":\"\",\"reason\":\"\",\"status\":64,\"notifystatus\":0,\"addtime\":1603095393,\"sendtime\":1603095393,\"recvtime\":1603095395,\"succtime\":1603095396,\"failtime\":0,\"notifytime\":0,\"modtime\":1603095395,\"priority\":1603102593,\"extended_data\":\"{\\\"transIdForCheckingAccount\\\":\\\"\\\",\\\"feeBizCode\\\":\\\"2005\\\",\\\"isTransfer\\\":false,\\\"billMerchantOrderId\\\":\\\"22010191646623441\\\",\\\"batchTransferInfoList\\\":\\\"[]\\\",\\\"withdrawReqType\\\":0,\\\"needNotifyClearingCenter\\\":true,\\\"unwithdrawToPayTransferWay\\\":\\\"actTransfer\\\",\\\"needNotifyTradeDataCenter\\\":true,\\\"billTradeType\\\":\\\"ZJ03\\\",\\\"billTradeId\\\":\\\"2340516932\\\",\\\"noAvailableCountMoney\\\":0,\\\"final_pay_company_code\\\":\\\"QDB\\\",\\\"billTradeChannel\\\":\\\"QD02\\\",\\\"hasSend\\\":\\\"1\\\",\\\"phyChannelCode\\\":\\\"netsunionAcs\\\",\\\"phyChannelName\\\":\\\"网联ACS全渠道\\\",\\\"logicalChannelAccountNo\\\":\\\"991100000111\\\",\\\"logicalChannelId\\\":181,\\\"payCoreMtpp\\\":\\\"mtpp_1095129106_11560614217\\\",\\\"billAccountTime\\\":0}\",\"card_id\":236548639,\"trade_no\":\"1602748863780\",\"iph_pay_merchant_no\":12000102584584,\"queuing_type\":0,\"pay_to_card_biz_req_code\":2005,\"pay_prod_type\":1,\"pay_to_card_account_type\":101,\"from_sub_account_id\":0,\"fee_code\":\"\",\"order_time\":1603095393000,\"fee\":0,\"return_fee\":1,\"start_pay_date\":0,\"error_code\":\"FT2O0000\",\"error_msg\":\"付款成功\"}";
    }
}
