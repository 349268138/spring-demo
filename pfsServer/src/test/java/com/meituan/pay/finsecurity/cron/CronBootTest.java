package com.meituan.pay.finsecurity.cron;

import com.meituan.pay.finsecurity.service.data.TradeEventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author hhhb
 * @date 2020/12/9 9:18 下午
 */
public class CronBootTest {

    @Spy
    @InjectMocks
    private CronBoot cronBoot;

    @Mock
    private TradeEventService tradeEventService;

    @Mock
    private Logger LOGGER;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(CronBoot.class, "logger", LOGGER);
    }

    @Test
    public void refreshTradeEventCacheTest() {
        cronBoot.refreshTradeEventCache();
        Mockito.verify(tradeEventService, Mockito.times(1)).refreshTradeEvent();
    }

    @Test
    public void refreshTradeEventCacheTest_error() {
        Mockito.doThrow(new RuntimeException()).when(tradeEventService).refreshTradeEvent();
        cronBoot.refreshTradeEventCache();
        verify(LOGGER, times(1)).error(anyString(), anyString());
    }
}
