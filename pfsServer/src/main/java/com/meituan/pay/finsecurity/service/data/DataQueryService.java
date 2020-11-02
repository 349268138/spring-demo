package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.JacksonUtils;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.script.GroovyScript;
import deps.redis.clients.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/23.
 */
@Service
public class DataQueryService {
    private static final Logger logger = LoggerFactory.getLogger(DataQueryService.class);

    @Autowired
    private DataQueryProcessorFactory dataQueryFactory;

    public String queryTradeData(List<DataRule> dataRuleList, String eventData) {
        try {
            String tradeData = obtainTradeData(dataRuleList, eventData);
            logger.info("queryTradeData success, dataRuleList: {}, eventData: {}, tradeData: {}", dataRuleList, eventData, tradeData);
            return tradeData;
        } catch (Exception e) {
            logger.error("queryTradeData error, dataRuleList: {}, exception: {}", dataRuleList, LoggerUtils.getStackTrace(e));
            throw new RuntimeException(String.format("queryTradeData error, dataRuleList: %s", dataRuleList), e);
        }
    }

    private String obtainTradeData(List<DataRule> dataRuleList, String eventData) throws Exception {
        if (CollectionUtils.isEmpty(dataRuleList)) {
            return StringUtils.EMPTY;
        }

        Map<String, Map> tradeData = new HashMap<>();
        for (DataRule dataRule : dataRuleList) {
            DataQueryProcessor processor = dataQueryFactory.obtainProcessor(dataRule.getType());
            String key = GroovyScript.obtainKey(eventData, dataRule.getKeyExpr());
            String data = processor.queryData(dataRule, key);
            tradeData.put(dataRule.getAlias(), JacksonUtils.jsonToMap(data));
        }

        return JacksonUtils.toJson(tradeData);
    }
}
