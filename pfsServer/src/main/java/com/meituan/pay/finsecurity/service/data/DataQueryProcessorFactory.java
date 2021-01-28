package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */

/**
 * 数据查询处理器工厂类
 */

@Service
public class DataQueryProcessorFactory {

    @Autowired
    private RpcDataQueryProcessor rpcDataQueryProcessor;

    @Autowired
    private SquirrelDataQueryProcessor squirrelDataQueryProcessor;

    public DataQueryProcessor obtainProcessor(DataAccessTypeEnum type) {
        if (DataAccessTypeEnum.RPC.equals(type)) {
            return rpcDataQueryProcessor;
        }

        if (DataAccessTypeEnum.SQUIRREL.equals(type)) {
            return squirrelDataQueryProcessor;
        }

        throw new RuntimeException(String.format("unsupport data access type: %s", type));
    }
}
