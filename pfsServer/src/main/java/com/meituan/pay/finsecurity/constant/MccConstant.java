package com.meituan.pay.finsecurity.constant;

/**
 * @author hhhb
 * @date 2020/11/4 5:50 下午
 */
public class MccConstant {
    public static final String EVENTDATAMAP_KEY = "eventdata_map_json";
    public static final String KEY_DEV = "key_dev";
    public static final String EVENTDATAMAP_VALUE = "{\"fundsRequest\":{\"dataRuleList\":[{\"address\":\"com.sankuai.pay.fundstransfer.paycore:9006:localhost\",\"alias\":\"paycore\",\"eventId\":1,\"id\":1,\"keyExpr\":\"eventData.trade_no\",\"name\":\"付款核心数据配置\",\"type\":\"RPC\"}],\"decisionRuleList\":[{\"alias\":\"decisionRule_01\",\"eventId\":1,\"expr\":\"true\",\"id\":1,\"name\":\"决策规则_01\",\"type\":\"ALARM\"}],\"eventRule\":{\"code\":\"fundsRequest\",\"id\":1,\"name\":\"eventRule_01\",\"vectorList\":[{\"alias\":\"er_01\",\"expr\":\"true\",\"name\":\"事件规则01\"}]}}}";
}
