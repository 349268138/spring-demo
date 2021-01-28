package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.po.DataRule;
import com.meituan.pay.finsecurity.sdk.api.DataQueryService;
import com.meituan.pay.finsecurity.sdk.dto.common.enums.ResStatusEnum;
import com.meituan.pay.finsecurity.sdk.dto.resp.TradeDataResp;
import com.meituan.service.mobile.mtthrift.proxy.ThriftClientProxy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */

/**
 *  RPC数据查询处理器
 */
@Service
public class RpcDataQueryProcessor implements DataQueryProcessor {
    private static final Logger logger = LoggerFactory.getLogger(RpcDataQueryProcessor.class);
    private static final int TIME_OUT = 2000;
    private static final String APPKEY = "com.sankuai.fundstransfer.finsecurity";
    private ConcurrentHashMap<String, ThriftClientProxy> thriftClientProxyHashMap = new ConcurrentHashMap<>();

    @Override
    public String queryData(DataRule dataRule, String key) {
        try {
            DataQueryService dataQueryService = getProxyService(dataRule.getAddress());
            TradeDataResp resp = dataQueryService.queryTradeData(key);
            if (ResStatusEnum.SUCCESS.equals(resp.getStatus())) {
                logger.info("rpc query data success. appkey: {}, key: {}, resp: {}", dataRule.getAddress(), key, resp);
                return resp.getData();
            }
            logger.error("rpc query data fail. appkey: {}, key: {}, resp: {}", dataRule.getAddress(), key, resp);
            throw new RuntimeException(String.format("query data fail. appkey: %s, key: %s", dataRule.getAddress(), key));
        } catch (Exception e) {
            logger.error("rpc query data error. appkey: {}, key: {}, exception: {}", dataRule.getAddress(), key, LoggerUtils.getStackTrace(e));
            throw new RuntimeException(String.format("rpc query data error. appkey: %s, key: %s", dataRule.getAddress(), key), e);
        }
    }

    protected DataQueryService getProxyService(String address) throws Exception {
        Optional<String[]> appkeyPortIp = getAppkeyPortIp(address);
        String mapKey = String.format("%s|%s|%s", appkeyPortIp.get()[0], appkeyPortIp.get()[1], appkeyPortIp.get()[2]);
        ThriftClientProxy proxy = thriftClientProxyHashMap.get(mapKey);

        if (proxy == null) {
            ThriftClientProxy thriftProxy = createThriftProxy(appkeyPortIp.get()[0], appkeyPortIp.get()[1], appkeyPortIp.get()[2], DataQueryService.class);
            thriftClientProxyHashMap.put(mapKey, thriftProxy);

            if (thriftClientProxyHashMap.size() > 1000) {
                logger.error("ThriftPoolService.thriftClientProxyHashMap.size>1000，需要确认是否数据规则配置有误");
            }
            return (DataQueryService) thriftProxy.getObject();
        }
        return (DataQueryService) proxy.getObject();
    }

    protected Optional<String[]> getAppkeyPortIp(String address) {
        String[] arrStr = address.split(":");
        String remoteKey = arrStr[0];
        String port = StringUtils.EMPTY;
        String ip = StringUtils.EMPTY;
        if (arrStr.length == 2) {
            port = arrStr[1];
        } else if (arrStr.length == 3) {
            port = arrStr[1];
            ip = arrStr[2];
        }
        return Optional.of(new String[]{remoteKey, port, ip});
    }


    protected ThriftClientProxy createThriftProxy(String remoteKey, String port, String ip, Class<?> serviceInterface) throws Exception {
        ThriftClientProxy proxy = new ThriftClientProxy();
        proxy.setRetryRequest(true);
        proxy.setAppKey(APPKEY);
        proxy.setRemoteAppkey(remoteKey);
        proxy.setServiceInterface(serviceInterface);
        if (StringUtils.isEmpty(port)) {
            proxy.setFilterByServiceName(true);
        } else {
            proxy.setRemoteServerPort(Integer.valueOf(port));
        }

        if (StringUtils.isNotBlank(port) && StringUtils.isNotBlank(ip)) {
            proxy.setServerIpPorts(ip + ":" + port);
            proxy.setRemoteUniProto(true);
        }

        proxy.setTimeout(TIME_OUT);
        proxy.afterPropertiesSet();
        return proxy;
    }
}
