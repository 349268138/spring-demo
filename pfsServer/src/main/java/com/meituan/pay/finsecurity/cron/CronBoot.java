package com.meituan.pay.finsecurity.cron;

import com.cip.crane.client.spring.annotation.Crane;
import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.service.data.TradeEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hhhb
 * @date 2020/12/9 4:29 下午
 */
@Component
public class CronBoot {

    private static Logger logger = LoggerFactory.getLogger(CronBoot.class);

    @Autowired
    private TradeEventService tradeEventService;

    @Crane("refresh_tradeEvent_cache")
    public void refreshTradeEventCache() {
        try {
            tradeEventService.refreshTradeEvent();
            logger.info("refresh tradeEvent cache success.");
        } catch (Exception e) {
            logger.error("refresh tradeEvent cache error, exception: {}", LoggerUtils.getStackTrace(e));
        }
    }
}
