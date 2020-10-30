package com.meituan.pay.finsecurity.service.dataquery;

import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
@Service
public class DataQueryProcessorFactory {

    @Autowired
    private RpcDataQueryProcessor rpcDataQueryProcessor;

    public DataQueryProcessor obtainProcessor(DataAccessTypeEnum type) {
        if (DataAccessTypeEnum.RPC.equals(type)) {
            return rpcDataQueryProcessor;
        }

        throw new RuntimeException(String.format("unsupport data access type: %", type));
    }
}
