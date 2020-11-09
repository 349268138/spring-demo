package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.service.mobile.mtthrift.proxy.ThriftClientProxy;
import org.apache.commons.lang.StringUtils;
import com.meituan.pay.finsecurity.sdk.api.DataQueryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

/**
 * @author hhhb
 * @date 2020/11/4 2:39 下午
 */
public class RpcDataQueryProcessorTest {

    @InjectMocks
    private RpcDataQueryProcessor rpcDataQueryProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void queryDataError() {
        try {
            rpcDataQueryProcessor.queryData(new DataRule(), "");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("query data error. appkey"));
        }
    }

    @Test
    public void getProxyServiceTest() throws Exception {
        String address = "com.sankuai.pay.fundstransfer.bankservice:9001";
        DataQueryService dataQueryService = rpcDataQueryProcessor.getProxyService(address);
        Assert.assertNotNull(dataQueryService);

        DataQueryService exist = rpcDataQueryProcessor.getProxyService(address);
        Assert.assertTrue(dataQueryService == exist);

        for (int loop = 0; loop < 1001; loop++) {
            address = "com.sankuai.pay.fundstransfer.bankservice:" + loop;
            dataQueryService = rpcDataQueryProcessor.getProxyService(address);
            Assert.assertNotNull(dataQueryService);
        }
    }

    @Test
    public void getAppkeyPortIpTest() {
        String address = "com.sankuai.pay.fundstransfer.bankservice:9001";
        Optional<String[]> optional = rpcDataQueryProcessor.getAppkeyPortIp(address);
        Assert.assertTrue(optional.get().length == 3);
        Assert.assertTrue(StringUtils.isNotEmpty(optional.get()[0]));
        Assert.assertTrue(StringUtils.isNotEmpty(optional.get()[1]));
        Assert.assertTrue(StringUtils.isEmpty(optional.get()[2]));

        address = address + ":localhost";
        rpcDataQueryProcessor.getAppkeyPortIp(address);
        optional = rpcDataQueryProcessor.getAppkeyPortIp(address);
        Assert.assertTrue(optional.get().length == 3);
        Assert.assertTrue(StringUtils.isNotEmpty(optional.get()[0]));
        Assert.assertTrue(StringUtils.isNotEmpty(optional.get()[1]));
        Assert.assertTrue(StringUtils.isNotEmpty(optional.get()[2]));
    }

    @Test
    public void createThriftProxyTest() throws Exception {
        String appkey = "com.sankuai.pay.fundstransfer.bankservice";
        String port = StringUtils.EMPTY;
        String ip = StringUtils.EMPTY;
        ThriftClientProxy proxy = rpcDataQueryProcessor.createThriftProxy(appkey, port, ip, DataQueryService.class);
        Assert.assertNotNull(proxy);
        Assert.assertTrue(proxy.isFilterByServiceName());
        Assert.assertTrue(!proxy.isRemoteUniProto());

        port = "9001";
        proxy = rpcDataQueryProcessor.createThriftProxy(appkey, port, ip, DataQueryService.class);
        Assert.assertNotNull(proxy);
        Assert.assertTrue(!proxy.isFilterByServiceName());
        Assert.assertTrue(!proxy.isRemoteUniProto());

        ip = "localhost";
        proxy = rpcDataQueryProcessor.createThriftProxy(appkey, port, ip, DataQueryService.class);
        Assert.assertNotNull(proxy);
        Assert.assertTrue(!proxy.isFilterByServiceName());
        Assert.assertTrue(proxy.isRemoteUniProto());
    }
}
