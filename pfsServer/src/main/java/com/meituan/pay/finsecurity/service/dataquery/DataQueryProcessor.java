package com.meituan.pay.finsecurity.service.dataquery;

import com.meituan.pay.finsecurity.po.DataRule;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
public interface DataQueryProcessor {
    String queryData(DataRule dataRule, String key) throws Exception;
}
