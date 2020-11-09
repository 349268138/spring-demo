package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author hhhb
 * @date 2020/11/4 2:37 下午
 */
public class DataQueryProcessorFactoryTest {

    @InjectMocks
    private DataQueryProcessorFactory dataQueryProcessorFactory;

    @Mock
    private RpcDataQueryProcessor rpcDataQueryProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obtainProcessorSuccess() {
        DataQueryProcessor dataQueryProcessor = dataQueryProcessorFactory.obtainProcessor(DataAccessTypeEnum.RPC);
        Assert.assertTrue(dataQueryProcessor instanceof RpcDataQueryProcessor);
    }

    @Test
    public void obtainProcessorError() {
        try {
            dataQueryProcessorFactory.obtainProcessor(DataAccessTypeEnum.SQUIRREL);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("unsupport data access"));
        }

    }
}
