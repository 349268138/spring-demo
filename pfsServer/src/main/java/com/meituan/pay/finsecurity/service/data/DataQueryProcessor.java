package com.meituan.pay.finsecurity.service.data;

import com.meituan.pay.finsecurity.po.DataRule;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */

/**
 *  查询处理器接口
 */
public interface DataQueryProcessor {
    String queryData(DataRule dataRule, String key);
}
