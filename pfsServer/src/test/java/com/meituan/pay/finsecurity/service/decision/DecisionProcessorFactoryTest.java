package com.meituan.pay.finsecurity.service.decision;

import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/11/9.
 */
public class DecisionProcessorFactoryTest {

    @InjectMocks
    private DecisionProcessorFactory decisionProcessorFactory;

    @Mock
    private MonitorDecisonProcessor monitorDecisonProcessor;

    @Mock
    private AlarmDecisonProcessor alarmDecisonProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtainProcessorError() {
        try {
            decisionProcessorFactory.obtainProcessor(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("unsupport decision type"));
        }
    }

    @Test
    public void obtainProcessorSuccess() {
        Assert.assertTrue(monitorDecisonProcessor == decisionProcessorFactory.obtainProcessor(TypeEnum.MONITOR));
        Assert.assertTrue(alarmDecisonProcessor == decisionProcessorFactory.obtainProcessor(TypeEnum.ALARM));
    }
}
