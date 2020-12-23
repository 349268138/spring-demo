package com.meituan.pay.finsecurity.service.data;

import com.meituan.funds.simple.util.LoggerUtils;
import com.meituan.pay.finsecurity.adapter.SquirrelAdapter;
import com.meituan.pay.finsecurity.constant.SquirrelConstant;
import com.meituan.pay.finsecurity.po.DataRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */

/**
 *  Squirrel数据查询处理器
 */

@Service
public class SquirrelDataQueryProcessor implements DataQueryProcessor {
    private static final Logger logger = LoggerFactory.getLogger(SquirrelDataQueryProcessor.class);

    @Autowired
    private SquirrelAdapter squirrelAdapter;

    @Override
    public String queryData(DataRule dataRule, String key) {
        try {
            String resp = obtainSquirrelData(dataRule.getAddress(), key);
            logger.info("squirrel query data success. category: {}, key: {}, resp: {}", dataRule.getAddress(), key, resp);
            return resp;
        } catch (Exception e) {
            logger.error("squirrel query data error. category: {}, key: {}, exception: {}", dataRule.getAddress(), key, LoggerUtils.getStackTrace(e));
            throw new RuntimeException(String.format("squirrel query data error. category: %s, key: %s", dataRule.getAddress(), key), e);
        }
    }

    /**
     * address：type:category
     */
    private String obtainSquirrelData(String address, String key) {
        String[] squirrelInfos = address.split(":");
        String type = squirrelInfos[0];
        String category = squirrelInfos[1];
        if (SquirrelConstant.TYPE_HASH.equals(type)) {
            return squirrelAdapter.hGetAll(category, key);
        }

        if (SquirrelConstant.TYPE_STRING.equals(type)) {
            return squirrelAdapter.mGet(category, otainKeys(key));
        }

        throw new RuntimeException(String.format("squirrel method not exsit. address: %s, key: %s", address, key));
    }

    private List<String> otainKeys(String key) {
        String[] keyArray = key.split(",");
        List<String> keyList = new ArrayList<>();
        for (String squirrelKey : keyArray) {
            keyList.add(squirrelKey.trim());
        }
        return keyList;
    }
}
