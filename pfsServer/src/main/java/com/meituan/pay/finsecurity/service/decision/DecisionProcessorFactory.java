package com.meituan.pay.finsecurity.service.decision;

import com.meituan.pay.finsecurity.po.enums.DataAccessTypeEnum;
import com.meituan.pay.finsecurity.po.enums.TypeEnum;
import com.meituan.pay.finsecurity.service.data.DataQueryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2020/10/27.
 */
@Service
public class DecisionProcessorFactory {

    @Autowired
    private MonitorDecisonProcessor monitorDecisonProcessor;

    @Autowired
    private AlarmDecisonProcessor alarmDecisonProcessor;

    public DecisionProcessor obtainProcessor(TypeEnum type) {
        if (TypeEnum.MONITOR == type) {
            return monitorDecisonProcessor;
        }

        if (TypeEnum.ALARM == type) {
            return alarmDecisonProcessor;
        }

        throw new RuntimeException(String.format("unsupport decision type: %s", type));
    }
}
